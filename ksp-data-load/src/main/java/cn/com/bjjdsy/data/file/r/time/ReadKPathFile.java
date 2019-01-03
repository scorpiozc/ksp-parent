package cn.com.bjjdsy.data.file.r.time;

import cn.com.bjjdsy.common.constant.CalcConstant;
import cn.com.bjjdsy.data.entity.time.KPath;
import cn.com.bjjdsy.data.file.AbstractReadDataFile;

public class ReadKPathFile extends AbstractReadDataFile {

	@Override
	public void parseData(String[] data) {
		KPath kpath = new KPath();
		kpath.setFromStation(data[0]);
		kpath.setToStation(data[1]);
		kpath.setKsn(data[2]);
		kpath.setKpath(data[4]);
		kpath.setTransferStation(data[7]);

//		ParsePath parsePath = new ParsePath();
//		parsePath.parse(kpath);
		CalcConstant.kPathList.add(kpath);
	}

}
