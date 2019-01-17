package cn.com.bjjdsy.data.file.r.path;

import java.util.ArrayList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.bjjdsy.common.config.CustomConfig;
import cn.com.bjjdsy.common.constant.CalcConstant;
import cn.com.bjjdsy.common.constant.CalcPathEnum;
import cn.com.bjjdsy.data.entity.path.Section;
import cn.com.bjjdsy.data.entity.path.Station;
import cn.com.bjjdsy.data.file.AbstractReadDataFile;

@Service
public class ReadTransferWalktimeFile extends AbstractReadDataFile {

	static final Logger logger = LoggerFactory.getLogger(ReadTransferWalktimeFile.class);
	@Autowired
	private CustomConfig customConfig;

	@Override
	public void parseData(String[] data) {
		int tsCode = Integer.parseInt(data[0]);
		int start = Integer.parseInt(data[1]);
		int end = Integer.parseInt(data[2]);
		int time = Integer.parseInt(data[3]);

		double departWeight = CalcConstant.DEPART_WEIGHT;
		boolean departAlphaOn = CalcConstant.DEPART_ALPHA_ON;
		Station[] stations = CalcConstant.stations;
		Map<Integer, ArrayList<Integer>> transferDict = CalcConstant.transferDict;
		Map<Integer, Integer> departIntervalTimes = CalcConstant.departIntervalTimesDict;
		Map<String, Integer> specials = customConfig.getFakeTransferDict();
//		System.out.println("specials:" + specials.size());

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
//		for (int code : transferDict.get(tsCode)) {
//
//		}

		// make the new line
		int traveltime;
		double impedance;
		if (specials.get(Integer.toString(startStation.getCode())) != null && specials.get(Integer.toString(startStation.getCode())) == endStation.getCode()) {
			traveltime = 0;
			impedance = 0;
		} else {
			traveltime = time + (int) (departIntervalTimes.get(end) * departWeight);
			impedance = (time + departIntervalTimes.get(end) * (departAlphaOn ? departWeight : 1)) * CalcPathEnum.RULE.getTransCoeff();
		}
		Section connect = new Section(startStation, endStation, null, CalcConstant.sectionId++, -1, 0, traveltime, impedance);
		startStation.addSection(connect);
		endStation.addSection(connect);
	}

}
