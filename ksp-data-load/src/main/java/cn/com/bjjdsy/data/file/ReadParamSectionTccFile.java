package cn.com.bjjdsy.data.file;

import cn.com.bjjdsy.calc.entity.ParamSectionTcc;
import cn.com.bjjdsy.common.CalcConstant;

public class ReadParamSectionTccFile extends AbstractReadDataFile {

	@Override
	public void parseData(String[] data) {
//		ParamSectionTcc section = new ParamSectionTcc();
//		section.setStationCode(data[2]);
//		section.setTccStationCode(data[3]);

		CalcConstant.paramSectionTccMap.put(data[3], data[2]);
	}

}
