package cn.com.bjjdsy.data.file;

import cn.com.bjjdsy.calc.entity.KPath;
import cn.com.bjjdsy.calc.path.ParsePath;
import cn.com.bjjdsy.common.CalcConstant;

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
