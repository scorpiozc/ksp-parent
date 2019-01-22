package cn.com.bjjdsy.calc.accessible.v2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.bjjdsy.common.config.CustomConfig;
import cn.com.bjjdsy.common.constant.CalcConstant;
import cn.com.bjjdsy.common.constant.CalcPathEnum;
import cn.com.bjjdsy.data.entity.db.ParamOdRouteAccessible;
import cn.com.bjjdsy.data.entity.path.Line;
import cn.com.bjjdsy.data.entity.path.Path;
import cn.com.bjjdsy.data.entity.path.Section;
import cn.com.bjjdsy.data.entity.path.Station;
import cn.com.bjjdsy.data.file.v2.ReadDataFile;

//@Service
public class AccessibleCalcEngine {

	private static final Logger logger = LoggerFactory.getLogger(AccessibleCalcEngine.class);
	@Autowired
	private CustomConfig customConfig;

//	private int k = 5;
	private int stationCounts;
	private int[] stationCodes;
	private int[] stationIndexes;
	// the generated shortest paths
	private Path paths[][][];
	private int pathCounts[][];
	private int sectionId;

	private Station[] stations;
	private Line[] lines;
	private String filepath;

	private Map<String, Integer> parktimesDict;
	private Map<Integer, Integer> departIntervalTimesDict;
	private HashMap<Integer, ArrayList<Integer>> transferDict;

	public void start(int k) {
		calcPath(k);
	}

	public AccessibleCalcEngine init() {
		stationCounts = 0;
		sectionId = 0;

		lines = new Line[CalcConstant.MAX_LINE];
		stations = new Station[CalcConstant.MAX_STATION];
		stationCodes = new int[CalcConstant.MAX_STATION];
		stationIndexes = new int[CalcConstant.MAX_STATION];
		for (int i = 0; i < CalcConstant.MAX_STATION; i++) {
			stationIndexes[i] = -1;
		}

		transferDict = new HashMap<>();
		parktimesDict = new HashMap<>();
		departIntervalTimesDict = new HashMap<>();

		return this;
	}

//	public AccessibleCalcEngine() {
//
//	}

	public AccessibleCalcEngine load(String versionCode) {
		this.filepath = customConfig.getFilepath() + versionCode;
		TreeMap<Integer, String> stationDict = new TreeMap<Integer, String>();
		HashMap<Integer, String> lineDict = new HashMap<Integer, String>();
		try {
			loadParktime();
			loadDepart();
			loadTransfer();
			loadStation(stationDict);
			loadLine(lineDict);
			genLine(lineDict);
			genStation(stationDict);
			loadSection();
			loadTransferWalktime();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return this;
	}

	private void loadParktime() throws IOException {
		List<String[]> list = ReadDataFile.read(filepath + CalcConstant.STATION_PARKTIME);
		list.forEach(data -> {
			int start = Integer.parseInt(data[1]);// start station
			int direct = Integer.parseInt(data[2]);// direct
			int stop = Integer.parseInt(data[3]);// stop station
			int parktime = Integer.parseInt(data[4]);// parktime
			parktimesDict.put(start + "-" + stop, parktime);
		});
		System.out.println("loadParktime:" + list.size() + " " + parktimesDict.size());
	}

	private void loadDepart() throws IOException {
		List<String[]> list = ReadDataFile.read(filepath + CalcConstant.LINE_DEPART_INTERVAL_TIME);
		list.forEach(data -> {
			int lineCode = Integer.parseInt(data[0]);
			int departIntervalTime = Integer.parseInt(data[2]);
			departIntervalTimesDict.put(lineCode, departIntervalTime);
		});
		System.out.println("loadDepart:" + list.size() + " " + departIntervalTimesDict.size());
	}

	private void loadTransfer() throws IOException {
		List<String[]> list = ReadDataFile.read(filepath + CalcConstant.TRANSFER_BASE_INFO);
		list.forEach(data -> {
			int station = Integer.parseInt(data[1]);
			int transferCode = Integer.parseInt(data[0]);
			if (transferDict.get(transferCode) == null)
				transferDict.put(transferCode, new ArrayList<Integer>());
			transferDict.get(transferCode).add(station);
		});
		System.out.println("loadTransfer:" + list.size() + " " + transferDict.size());
	}

	private void loadStation(Map<Integer, String> stationDict) throws IOException {
		List<String[]> list = ReadDataFile.read(filepath + CalcConstant.STATION_BASE_INFO);
		list.forEach(data -> {
			int stationCode = Integer.parseInt(data[0]);
			String stationName = data[1];
			stationDict.put(stationCode, stationName);
		});
		System.out.println("loadStation:" + list.size() + " " + stationDict.size());
	}

	private void loadLine(Map<Integer, String> lineDict) throws IOException {
		List<String[]> list = ReadDataFile.read(filepath + CalcConstant.LINE_BASE_INFO);
		list.forEach(data -> {
			int lineCode = Integer.parseInt(data[0]);
			String lineName = data[1];
			lineDict.put(lineCode, lineName);
		});
		System.out.println("loadLine:" + list.size() + " " + lineDict.size());
	}

	private void genLine(HashMap<Integer, String> lineDict) {
		for (Integer code : lineDict.keySet()) {
			lines[code] = new Line(code, CalcConstant.lineDict.get(code));
		}
	}

	private void genStation(TreeMap<Integer, String> stationDict) {
		for (Integer code : stationDict.keySet()) {
			stationIndexes[code] = stationCounts;
			stationCodes[stationCounts++] = code;
			stations[code] = new Station(code, null, stationDict.get(code));

		}
	}

	private void loadSection() throws IOException {
		List<String[]> list = ReadDataFile.read(filepath + CalcConstant.SECTION_BASE_INFO);
		list.forEach(data -> {
			int lineCode = Integer.parseInt(data[0]);
			int s1 = Integer.parseInt(data[1]);
			int s2 = Integer.parseInt(data[2]);
			int dist = Integer.parseInt(data[3]);
			int time = Integer.parseInt(data[4]);
			int direction = Integer.parseInt(data[5]);

			// check that the stations are valid
			if (stations[s1].getLine() != null && stations[s1].getLine().getCode() != lineCode) {
				System.out.println(stations[s1].getLine().getCode() + " " + stations[s1].getCode() + " " + lineCode);
				throw new IllegalArgumentException("Input first station is conflicting.");
			}
			if (stations[s2].getLine() != null && stations[s2].getLine().getCode() != lineCode) {
				System.out.println(stations[s2].getLine().getCode() + " " + stations[s2].getCode() + " " + lineCode);
				throw new IllegalArgumentException("Input first station is conflicting.");
			}

			// set the tracks that they are on
			stations[s1].setLine(lines[lineCode]);
			stations[s2].setLine(lines[lineCode]);

			// create a new Line
			int traveltime = time + (parktimesDict.get(s1 + "-" + s2) == null ? 0 : parktimesDict.get(s1 + "-" + s2));
			double impedance = traveltime;
			Section section = new Section(stations[s1], stations[s2], lines[lineCode], sectionId++, direction, dist, traveltime, impedance);
			stations[s1].addSection(section);
			stations[s2].addSection(section);
		});
		System.out.println("loadSection:" + list.size());
	}

	private void loadTransferWalktime() throws IOException {
		List<String[]> list = ReadDataFile.read(filepath + CalcConstant.TRANSFER_LINE_WALKTIME_INFO);
		list.forEach(data -> {
			int tsCode = Integer.parseInt(data[0]);
			int start = Integer.parseInt(data[1]);
			int end = Integer.parseInt(data[2]);
			int time = Integer.parseInt(data[3]);

			double departWeight = CalcConstant.DEPART_WEIGHT;
			boolean departAlphaOn = CalcConstant.DEPART_ALPHA_ON;
			Map<String, Integer> specials = customConfig.getFakeTransferDict();
//			System.out.println("specials:" + specials.size());

			// find the start and end station
			Station startStation = null, endStation = null;
			for (int code : transferDict.get(tsCode)) {
				if ((stations[code].getLine().getCode()) == start) {
					startStation = stations[code];
					startStation.setTsCode(tsCode);
				}
				if ((stations[code].getLine().getCode()) == end) {
					endStation = stations[code];
					endStation.setTsCode(tsCode);
				}
			}
//			for (int code : transferDict.get(tsCode)) {
			//
//			}

			// make the new line
			int traveltime;
			double impedance;
			if (specials.get(Integer.toString(startStation.getCode())) != null && specials.get(Integer.toString(startStation.getCode())) == endStation.getCode()) {
				traveltime = 0;
				impedance = 0;
			} else {
				traveltime = time + (int) (departIntervalTimesDict.get(end) * departWeight);
				impedance = (time + departIntervalTimesDict.get(end) * (departAlphaOn ? departWeight : 1)) * CalcPathEnum.RULE.getTransCoeff();
			}
			Section section = new Section(startStation, endStation, null, sectionId++, -1, 0, traveltime, impedance);
			startStation.addSection(section);
			endStation.addSection(section);
		});
		System.out.println("loadTransferWalktime:" + list.size());
	}

	int p = 0;
	int d = 0;

	private void calcPath(int k) {
		paths = new Path[stationCounts][stationCounts][k];
		pathCounts = new int[stationCounts][stationCounts];// 0

		System.out.println("stationCounts:" + stationCounts);
		int ii = 0;
		for (int i : stationIndexes) {
			if (i == -1) {
				ii++;
			}
		}
		System.out.println("stationIndexes:" + ii);

		int ss = 0;
		for (Station s : stations) {
			if (s == null) {
				ss++;
			}
		}
		System.out.println("stations:" + ss);

		for (int i = 0; i < stationCounts; ++i) {
			calc(i, k);
		}
		System.out.println("calc path:" + p);
		p = 0;
		System.out.println("del double tras:" + d);
		d = 0;
	}

	private void calc(int startIdx, int k) {
		Station source = stations[stationCodes[startIdx]];
//		System.out.printf("source station:%d\n", source.getCode());
		PriorityQueue<Path> pathQueue = new PriorityQueue<Path>();
		// create the first path
		Path begin = new Path();
		begin.addStation(source);
		pathQueue.add(begin);

		// iterate through possible paths
		while (!pathQueue.isEmpty()) {
			// get the shortest path and last station
			Path shortest = pathQueue.poll();
			Station lastStation = shortest.getEndStation();
			int endIdx = stationIndexes[lastStation.getCode()];
			// if enough paths have been found continue
			if (pathCounts[startIdx][endIdx] >= k) {
				continue;
			}
			Path tmp = new Path(shortest);
			paths[startIdx][endIdx][pathCounts[startIdx][endIdx]++] = tmp;

			boolean isTransfer = this.restorePathTimeAndImpedance(shortest, lastStation);
			boolean isDouble = false;
			// iterate through the neighboring nodes
			for (Station next : lastStation.getNeighbors()) {
				// if it creates a cycle continue
				if (shortest.getStations().contains(next)) {
					continue;
				}
				// create the new path
				Path newPath = new Path(shortest);
				Section section = lastStation.getSection(next);
				// skip successive transfer
				if (section.getDirection() == -1 && isTransfer) {
					continue;
				}

				if (section.getDirection() == -1) {

				} else {
					newPath.addTime(section.getTime());
					newPath.addImpedance(section.getImpedance());
				}
				newPath.addStation(next, section);
				// add the path
				int tsCode = lastStation.getTsCode();
				if (lastStation.getTsCode() != 0) {
					// delect double transfer
					if (newPath.getUnTransMap().get(tsCode) == null) {
						newPath.getUnTransMap().put(tsCode, 0);
					}
					// transfer section +1 other +2
					if (section.getDirection() == -1) {
						int i = newPath.getUnTransMap().get(tsCode);
						newPath.getUnTransMap().put(tsCode, i + 1);
					} else {
						int i = newPath.getUnTransMap().get(tsCode);
						newPath.getUnTransMap().put(tsCode, i + 2);
					}
					if (newPath.getUnTransMap().get(tsCode) > 3) {
						isDouble = true;
						paths[startIdx][endIdx][--pathCounts[startIdx][endIdx]] = null;
						d++;
						break;
					}
				}
				pathQueue.add(newPath);
				p++;
			}
		}
//		System.out.println("del double tras:" + d);
	}

	private boolean restorePathTimeAndImpedance(Path shortest, Station lastStation) {
		boolean isTransfer = false;
		int time = 0;
		double impedance = 0;
		if (shortest.getStations().size() == 2) {
			Station prev = shortest.getPrevStation(lastStation);
			if (prev.getSection(lastStation).getDirection() == -1) {
				// System.out.println("prev:" + prev.getCode() + "-" + lastStation.getCode());
				isTransfer = true;
			}
		}
		if (shortest.getStations().size() > 2) {// skip start when its transfer
			Station prev = shortest.getPrevStation(lastStation);
			if (prev.getSection(lastStation).getDirection() == -1) {
				d++;
				isTransfer = true;
				time = prev.getSection(lastStation).getTime();
				shortest.setTime(shortest.getTime() + time);
				impedance = prev.getSection(lastStation).getImpedance();
				shortest.setImpedance(shortest.getImpedance() + impedance);
			}
		}
		return isTransfer;
	}

	public List<ParamOdRouteAccessible> printPath(int start, int end, String versionCode) {
		List<ParamOdRouteAccessible> list = new ArrayList<>();
		String filepath = customConfig.getFilepath() + versionCode;
		String filename = filepath + "/" + start + "-" + end + ".txt";
		start = stationIndexes[start];
		end = stationIndexes[end];
		if (start < 0 || end < 0)
			throw new IllegalArgumentException();
		// output the number of paths and the distance
		// output the paths themselves
		try (FileOutputStream fos = new FileOutputStream(filename);) {
			if (CalcConstant.PATHOUTPUT) {
				this.print(start, end, list, fos, versionCode);
			}
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}

	int c = 0;
	int cc = 0;

	public List<ParamOdRouteAccessible> printPath(String versionCode) {

		List<ParamOdRouteAccessible> list = new ArrayList<>();
		String filepath = customConfig.getFilepath() + versionCode;
		String filename = filepath + "/" + "accessible-path.txt";
		try (FileOutputStream fos = new FileOutputStream(filename);) {
			for (int start = 0; start < stationCounts; ++start) {
				for (int end = 0; end < stationCounts; ++end) {
					if (start != end) {
						cc += pathCounts[start][end];
						// IOUtils.write(stationCodes[start] + ":" + stationCodes[end] + ":" +
						// pathCounts[start][end] + "\n", fos);
//						logger.info("{} {}", start, end);
						if (CalcConstant.PATHOUTPUT) {
							this.print(start, end, list, fos, versionCode);
						}
					}
				}
			}
			System.out.println("path counts:" + c + " " + cc);
			c = 0;
			cc = 0;
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}

	private List<ParamOdRouteAccessible> print(int start, int end, List<ParamOdRouteAccessible> list, FileOutputStream fos, String versionCode) throws IOException {
		StringBuffer routeStationCode = new StringBuffer();
		StringBuffer routeLineCode = new StringBuffer();
		StringBuffer routeTransferCode = new StringBuffer();

		// go through each path and print it out
		List<Integer> tList = new ArrayList<>();
		for (int i = 0; i < pathCounts[start][end]; ++i) {
			c++;
			Path cur = paths[start][end][i];
			ArrayList<Station> stations = cur.getStations();
			ArrayList<Section> sections = cur.getSections();

			for (int j = 0; j < sections.size(); ++j) {
				if (sections.get(j).getDirection() == -1) {
					routeTransferCode.append(sections.get(j).getStart().getCode() + ";" + sections.get(j).getEnd().getCode() + "-");
					if (j != 0 && j != sections.size() - 1) {
						tList.add(j);
					}
				} else {

				}
				routeStationCode.append(stations.get(j).getCode() + "-");
			}
			routeStationCode.append(stations.get(sections.size()).getCode());

			if (tList.isEmpty()) {
				routeLineCode.append(stations.get(sections.size() - 1).getLine().getCode());
			} else {
				for (int t : tList) {
					routeLineCode.append(sections.get(t).getStart().getLine().getCode() + "-");
				}
				routeLineCode.append(stations.get(tList.get(tList.size() - 1) + 1).getLine().getCode());
			}

//			IOUtils.write(stationCodes[start] + "," + stationCodes[end] + "," + (i + 1) + "," + routeLineCode + "," + routeStationCode + "," + cur.getTime() + "," + cur.getImpedance() + ","
//					+ (routeTransferCode.length() == 0 ? "" : String.valueOf(routeTransferCode.substring(0, routeTransferCode.length() - 1))) + "\n", fos);

			ParamOdRouteAccessible odRoute = new ParamOdRouteAccessible();
			odRoute.setoStationCode((short) stationCodes[start]);
			odRoute.setdStationCode((short) stationCodes[end]);
			odRoute.setRouteNo((short) (i + 1));
			odRoute.setRouteLineCode(String.valueOf(routeLineCode));
			odRoute.setRouteStationCode(String.valueOf(routeStationCode));
			odRoute.setCostTime(cur.getTime());
			odRoute.setImpedanceTime(new Double(cur.getImpedance()).intValue());
			odRoute.setRouteTransferCode(routeTransferCode.length() == 0 ? "" : String.valueOf(routeTransferCode.substring(0, routeTransferCode.length() - 1)));
			odRoute.setVersionCode(versionCode);
			list.add(odRoute);

			routeStationCode.setLength(0);
			routeLineCode.setLength(0);
			routeTransferCode.setLength(0);
			tList.clear();
		}
		return list;
	}

}
