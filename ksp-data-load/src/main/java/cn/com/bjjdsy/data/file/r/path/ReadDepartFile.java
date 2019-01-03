package cn.com.bjjdsy.data.file.r.path;

import org.springframework.stereotype.Service;

import cn.com.bjjdsy.common.constant.CalcConstant;
import cn.com.bjjdsy.data.file.AbstractReadDataFile;

@Service
public class ReadDepartFile extends AbstractReadDataFile {

	@Override
	public void parseData(String[] data) {
		int lineCode = Integer.parseInt(data[0]);
		int departIntervalTime = Integer.parseInt(data[2]);

		// add the depart
		CalcConstant.departIntervalTimesDict.put(lineCode, departIntervalTime * 60);
	}

}
