package cn.com.bjjdsy.data.file.r.path;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.bjjdsy.common.constant.CalcConstant;
import cn.com.bjjdsy.data.file.AbstractReadDataFile;

@Service
public class ReadTransferFile extends AbstractReadDataFile {

	@Override
	public void parseData(String[] data) {
		int station = Integer.parseInt(data[1]);
		int transferCode = Integer.parseInt(data[0]);
		// put the code in
		Map<Integer, ArrayList<Integer>> transferDict = CalcConstant.transferDict;
		if (transferDict.get(transferCode) == null)
			transferDict.put(transferCode, new ArrayList<Integer>());
		// add the station
		transferDict.get(transferCode).add(station);
	}

}
