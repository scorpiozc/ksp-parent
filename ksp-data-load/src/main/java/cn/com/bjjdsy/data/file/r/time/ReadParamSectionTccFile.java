package cn.com.bjjdsy.data.file.r.time;

import cn.com.bjjdsy.common.constant.CalcConstant;
import cn.com.bjjdsy.data.file.AbstractReadDataFile;

public class ReadParamSectionTccFile extends AbstractReadDataFile {

	@Override
	public void parseData(String[] data) {
//		ParamSectionTcc section = new ParamSectionTcc();
//		section.setStationCode(data[2]);
//		section.setTccStationCode(data[3]);

		CalcConstant.paramSectionTccMap.put(data[3], data[2]);
	}

}
