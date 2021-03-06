package cn.com.bjjdsy.data.file.r.time;

import cn.com.bjjdsy.common.constant.CalcConstant;
import cn.com.bjjdsy.data.entity.time.Section;
import cn.com.bjjdsy.data.file.AbstractReadDataFile;

public class ReadSectionFile extends AbstractReadDataFile {

	@Override
	public void parseData(String[] data) {
		Section section = new Section();
		section.setStartStation(data[1]);
		section.setStopStation(data[2]);
		section.setDirect(Integer.parseInt(data[5]) == 0 ? 2 : 1);
		CalcConstant.sectionDict.put(section.getStartStation() + ":" + section.getStopStation(), section.getDirect());
	}

}
