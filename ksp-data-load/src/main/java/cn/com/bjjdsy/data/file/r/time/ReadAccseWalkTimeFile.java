package cn.com.bjjdsy.data.file.r.time;

import cn.com.bjjdsy.common.constant.CalcConstant;
import cn.com.bjjdsy.data.entity.time.AccseWalkTime;
import cn.com.bjjdsy.data.file.AbstractReadDataFile;

public class ReadAccseWalkTimeFile extends AbstractReadDataFile {

	@Override
	public void parseData(String[] data) {
		AccseWalkTime walkTime = new AccseWalkTime();
		walkTime.setFromAccStationCode(data[0]);
		walkTime.setToAccStationCode(data[1]);
		walkTime.setDateType(Integer.parseInt(data[2]));
		walkTime.setTimeAttr(Integer.parseInt(data[3]));
		walkTime.setFromDirect(Integer.parseInt(data[4]));
		walkTime.setToDirect(Integer.parseInt(data[5]));
		walkTime.setQuick(Integer.parseInt(data[6]));
		walkTime.setMiddle(Integer.parseInt(data[7]));
		walkTime.setSlow(Integer.parseInt(data[8]));
		walkTime.setVersionId(data[9]);
		String key = walkTime.getFromAccStationCode() + ":" + walkTime.getToAccStationCode() + ":"
				+ walkTime.getDateType() + ":" + walkTime.getTimeAttr() + ":" + walkTime.getFromDirect() + ":"
				+ walkTime.getToDirect();
		CalcConstant.accseWalkTimeDict.put(key, walkTime);
	}

}
