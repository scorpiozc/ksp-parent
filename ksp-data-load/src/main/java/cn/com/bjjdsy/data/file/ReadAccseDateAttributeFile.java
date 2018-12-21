package cn.com.bjjdsy.data.file;

import cn.com.bjjdsy.calc.entity.AccseDateAttribute;
import cn.com.bjjdsy.common.CalcConstant;

public class ReadAccseDateAttributeFile extends AbstractReadDataFile {

	@Override
	public void parseData(String[] data) {
		AccseDateAttribute dateAttr = new AccseDateAttribute();
		dateAttr.setCmDate(data[0]);
		dateAttr.setSection(Integer.parseInt(data[1]));
		dateAttr.setDateType(Integer.parseInt(data[2]));
		dateAttr.setVersion(data[3]);
		String key = dateAttr.getCmDate().split(" ")[0].replaceAll("/", "");
//		System.out.println("cmDate:" + key);
		CalcConstant.accseDateAttributeDict.put(key, dateAttr);
	}

}
