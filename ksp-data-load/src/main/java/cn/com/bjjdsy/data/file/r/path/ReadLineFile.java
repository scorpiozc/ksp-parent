package cn.com.bjjdsy.data.file.r.path;

import org.springframework.stereotype.Service;

import cn.com.bjjdsy.common.constant.CalcConstant;
import cn.com.bjjdsy.data.file.AbstractReadDataFile;

@Service
public class ReadLineFile extends AbstractReadDataFile {

	@Override
	public void parseData(String[] data) {
		int lineCode = Integer.parseInt(data[0]);
		String lineName = data[1];
		CalcConstant.lineDict.put(lineCode, lineName);
	}

}
