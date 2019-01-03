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
public class WriteParktimeFile {

	private static final Logger logger = LoggerFactory.getLogger(WriteParktimeFile.class);

	public void createStationParktimeFile(String filepath, String versionCode, List<ParamSection> sections) {
		try (FileOutputStream fos = new FileOutputStream(filepath + CalcConstant.STATION_PARKTIME)) {
			for (ParamSection section : sections) {
				IOUtils.write(this.stationParktimeToString(section), fos);
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private String stationParktimeToString(ParamSection paramSection) {
		return paramSection.getLineCode() + "," + paramSection.getoStationCode() + "," + paramSection.getDirection()
				+ "," + paramSection.getdStationCode() + "," + paramSection.getStartStationStopTime() + "\n";
	}
}
