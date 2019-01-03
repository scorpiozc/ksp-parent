package cn.com.bjjdsy.data.file.w.path;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.bjjdsy.common.constant.CalcConstant;
import cn.com.bjjdsy.data.entity.db.ParamSection;

@Service
public class WriteSectionFile {

	private static final Logger logger = LoggerFactory.getLogger(WriteSectionFile.class);

	public void createSectionBaseInfoFile(String filepath, String versionCode, List<ParamSection> sections) {
		try (FileOutputStream fos = new FileOutputStream(filepath + CalcConstant.SECTION_BASE_INFO)) {
			for (ParamSection section : sections) {
				IOUtils.write(this.sectionBaseInfoToString(section), fos);
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

	}

	private String sectionBaseInfoToString(ParamSection paramSection) {
		return paramSection.getLineCode() + "," + paramSection.getoStationCode() + "," + paramSection.getdStationCode()
				+ "," + paramSection.getSectionDistance() + "," + paramSection.getSectionRunTime() + ","
				+ paramSection.getDirection() + "\n";
	}
}
