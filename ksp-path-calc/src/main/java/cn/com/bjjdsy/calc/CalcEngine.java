package cn.com.bjjdsy.calc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.bjjdsy.calc.entity.ReachPath;
import cn.com.bjjdsy.common.constant.CalcConstant;
import cn.com.bjjdsy.data.entity.path.Line;
import cn.com.bjjdsy.data.entity.path.Path;
import cn.com.bjjdsy.data.entity.path.Section;
import cn.com.bjjdsy.data.entity.path.Station;

@Service
public class CalcEngine {

	private static final Logger logger = LoggerFactory.getLogger(CalcEngine.class);

//	private int k = 5;
	private int stationCounts;
	private int[] stationCodes;
	private int[] stationIndexes;
	// the generated shortest paths
	private Path paths[][][];
	private int pathCounts[][];

	private Station[] stations;
//	private Line[] lines;

	public void start(int k) {
		init();
		calcPath(k);
	}

	private void init() {
		stationCounts = CalcConstant.stationCounts;
		stationCodes = CalcConstant.stationCodes;
		stationIndexes = CalcConstant.stationIndexes;
		stations = CalcConstant.stations;
//		lines = CalcConstant.lines;
	}

	private void calcPath(int k) {
		paths = new Path[stationCounts][stationCounts][k];
		pathCounts = new int[stationCounts][stationCounts];// 0
		for (int i = 0; i < stationCounts; ++i) {
			calc(i, k);
		}
	}

	private void calc(int startIdx, int k) {
		Station source = stations[stationCodes[startIdx]];
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
			paths[startIdx][endIdx][pathCounts[startIdx][endIdx]++] = new Path(shortest);

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
						break;
					}
				}
				pathQueue.add(newPath);

			}
		}
	}

	private boolean restorePathTimeAndImpedance(Path shortest, Station lastStation) {
		boolean isTransfer = false;
		int time = 0;
		double impedance = 0;
		if (shortest.getStations().size() == 2) {
			Station prev = shortest.getPrevStation(lastStation);
			if (prev.getSection(lastStation).getDirection() == -1) {
				isTransfer = true;
			}
		}
		if (shortest.getStations().size() > 2) {// skip start when its transfer
			Station prev = shortest.getPrevStation(lastStation);
			if (prev.getSection(lastStation).getDirection() == -1) {
				isTransfer = true;
				time = prev.getSection(lastStation).getTime();
				shortest.setTime(shortest.getTime() + time);
				impedance = prev.getSection(lastStation).getImpedance();
				shortest.setImpedance(shortest.getImpedance() + impedance);
			}
		}
		return isTransfer;
	}

	public List<ReachPath> printPath(int start, int end) {
		String filename = start + "-" + end + ".txt";
		start = stationIndexes[start];
		end = stationIndexes[end];
		if (start < 0 || end < 0)
			throw new IllegalArgumentException();
		// output the number of paths and the distance
		// output the paths themselves
		if (CalcConstant.PATHOUTPUT) {
			return this.print(start, end, filename);
		}
		return null;
	}

	public void printPath() {
		for (int start = 0; start < stationCounts; ++start) {
			for (int end = 0; end < stationCounts; ++end) {
				if (start != end) {
					if (CalcConstant.PATHOUTPUT) {
						this.print(start, end, "reach-path.txt");
					}
				}
			}
		}
	}

	private List<ReachPath> print(int start, int end, String filename) {
		StringBuffer routeStationCode = new StringBuffer();
		StringBuffer routeLineCode = new StringBuffer();
		StringBuffer routeTransferCode = new StringBuffer();
		List<ReachPath> list = new ArrayList<>();
		// go through each path and print it out
		try (PrintWriter fout = new PrintWriter(new File(filename));) {
			List<Integer> tList = new ArrayList<>();
			for (int i = 0; i < pathCounts[start][end]; ++i) {
				ReachPath reachPath = new ReachPath();
				Path cur = paths[start][end][i];
				ArrayList<Station> stations = cur.getStations();
				ArrayList<Section> sections = cur.getSections();

				for (int j = 0; j < sections.size(); ++j) {
					if (sections.get(j).getDirection() == -1) {
						routeTransferCode.append(
								sections.get(j).getStart().getCode() + ";" + sections.get(j).getEnd().getCode() + "-");
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

//				fout.printf("%s,%s,%d,%s,%s,%d,%.2f\n", stationCodes[start], stationCodes[end], i + 1, routeLineCode,
//						routeStationCode, cur.getTime(), cur.getImpedance() / 60);
				reachPath.setFromStation(String.valueOf(stationCodes[start]));
				reachPath.setToStation(String.valueOf(stationCodes[end]));
				reachPath.setSn(String.valueOf(i + 1));
				reachPath.setPathLine(String.valueOf(routeLineCode));
				reachPath.setPathStation(String.valueOf(routeStationCode));
				reachPath.setTime(String.valueOf(cur.getTime()));
				reachPath.setInpedance(String.format("%.2f", cur.getImpedance() / 60));
				reachPath.setPathTransfer(
						String.valueOf(routeTransferCode.substring(0, routeTransferCode.length() - 1)));
				list.add(reachPath);
				routeStationCode.setLength(0);
				routeLineCode.setLength(0);
				routeTransferCode.setLength(0);
				tList.clear();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}

}
