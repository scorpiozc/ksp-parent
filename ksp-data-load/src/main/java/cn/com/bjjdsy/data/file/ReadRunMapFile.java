package cn.com.bjjdsy.data.file;

import java.util.ArrayList;
import java.util.HashMap;

import cn.com.bjjdsy.calc.entity.RunMap;
import cn.com.bjjdsy.calc.entity.RunMapKey;
import cn.com.bjjdsy.common.CalcConstant;

public class ReadRunMapFile extends AbstractReadDataFile {

	@Override
	public void parseData(String[] data) {
		RunMap runMap = new RunMap();
		runMap.setArrTime(data[8]);
		runMap.setDepTime(data[9]);
		runMap.setDestId(data[5]);
		runMap.setDirection(Integer.parseInt(data[10]));
		runMap.setLineNo(data[0]);
		runMap.setPltfId(CalcConstant.paramSectionTccMap.get(data[7]));
		runMap.setPltfSn(Integer.parseInt(data[6]));
		runMap.setSchDate(data[1]);
		runMap.setTripNo(data[4]);

		// gen runmapkey
		String key = runMap.getPltfId() + ":" + runMap.getDirection();
		RunMapKey runMapKey = new RunMapKey();
		runMapKey.setDepTime(data[9]);
		runMapKey.setLineNo(data[0]);
		runMapKey.setTripNo(data[4]);
//		logger.info("read depTime:{}", data[9]);
		if ("113:2".equals(key)) {
//			System.out.println("ReadRunMapFile: " + runMapKey.getDepTime() + ":" + runMapKey.getTripNo());
		}
		if (CalcConstant.runMapKeyMap.get(key) == null) {
			CalcConstant.runMapKeyMap.put(key, new ArrayList<>());
		}
		CalcConstant.runMapKeyMap.get(key).add(runMapKey);

//		CalcConstant.runMapKeyMap.forEach((kkk, value) -> {
//
//			logger.info("key:{}", kkk);
//			value.forEach(k -> {
//				logger.info("depTime:{}", k.getDepTime());
//			});
//		});

		// gen runmap
		String key2 = runMap.getLineNo() + ":" + runMap.getTripNo();
		if (CalcConstant.runMapMap.get(key2) == null) {
			CalcConstant.runMapMap.put(key2, new HashMap<>());
		}
		CalcConstant.runMapMap.get(key2).put(runMap.getPltfId(), runMap);

		if (CalcConstant.deptimeMap.get(runMap.getPltfId()) == null) {
			CalcConstant.deptimeMap.put(runMap.getPltfId(), new ArrayList<>());
		}
		CalcConstant.deptimeMap.get(runMap.getPltfId()).add(runMap.getDepTime());
	}

}
