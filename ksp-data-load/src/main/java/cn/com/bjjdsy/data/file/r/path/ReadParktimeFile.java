package cn.com.bjjdsy.data.file.r.path;

import org.springframework.stereotype.Service;

import cn.com.bjjdsy.common.constant.CalcConstant;
import cn.com.bjjdsy.data.file.AbstractReadDataFile;

@Service
public class ReadParktimeFile extends AbstractReadDataFile {

	@Override
	public void parseData(String[] data) {
		int start = Integer.parseInt(data[1]);// start station
		int direct = Integer.parseInt(data[2]);// direct
		int stop = Integer.parseInt(data[3]);// stop station
		int parktime = Integer.parseInt(data[4]);// parktime
		CalcConstant.parktimesDict.put(start + "-" + stop, parktime);
	}

}
