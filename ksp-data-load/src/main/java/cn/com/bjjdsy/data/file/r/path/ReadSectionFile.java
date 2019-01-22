package cn.com.bjjdsy.data.file.r.path;

import org.springframework.stereotype.Service;

import cn.com.bjjdsy.common.constant.CalcConstant;
import cn.com.bjjdsy.data.entity.path.Line;
import cn.com.bjjdsy.data.entity.path.Section;
import cn.com.bjjdsy.data.entity.path.Station;
import cn.com.bjjdsy.data.file.AbstractReadDataFile;

@Service
public class ReadSectionFile extends AbstractReadDataFile {

	@Override
	public void parseData(String[] data) {
		int lineCode = Integer.parseInt(data[0]);
		int s1 = Integer.parseInt(data[1]);
		int s2 = Integer.parseInt(data[2]);
		int dist = Integer.parseInt(data[3]);
		int time = Integer.parseInt(data[4]);
		int direction = Integer.parseInt(data[5]);

		Station[] stations = CalcConstant.stations;
		Line[] lines = CalcConstant.lines;
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
		int traveltime = time + (CalcConstant.parktimesDict.get(s1 + "-" + s2) == null ? 0
				: CalcConstant.parktimesDict.get(s1 + "-" + s2));
		double impedance = traveltime;
		Section section = new Section(stations[s1], stations[s2], lines[lineCode], CalcConstant.sectionId++, direction,
				dist, traveltime, impedance);
//		System.out.printf("s1:%s-s2:%s\n", s1, s2);
		stations[s1].addSection(section);
		stations[s2].addSection(section);
	}

}
