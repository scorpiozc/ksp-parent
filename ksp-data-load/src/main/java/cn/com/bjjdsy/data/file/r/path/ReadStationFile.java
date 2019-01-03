package cn.com.bjjdsy.data.file.r.path;

import org.springframework.stereotype.Service;

import cn.com.bjjdsy.common.constant.CalcConstant;
import cn.com.bjjdsy.data.file.AbstractReadDataFile;

@Service
public class ReadStationFile extends AbstractReadDataFile {

	@Override
	public void parseData(String[] data) {
		int stationCode = Integer.parseInt(data[0]);
		String stationName = data[1];
		CalcConstant.stationDict.put(stationCode, stationName);
	}

}
