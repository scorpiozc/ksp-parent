package cn.com.bjjdsy.data.file.w.path;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.bjjdsy.common.constant.CalcConstant;
import cn.com.bjjdsy.data.entity.db.ParamStation;

@Service
public class WriteStationFile {

	private static final Logger logger = LoggerFactory.getLogger(WriteStationFile.class);

	public void createStationBaseInfoFile(String filepath, String versionCode, List<ParamStation> stations) {
		try (FileOutputStream fos = new FileOutputStream(filepath + CalcConstant.STATION_BASE_INFO)) {
			for (ParamStation station : stations) {
				IOUtils.write(this.stationBaseInfoToString(station), fos);
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private String stationBaseInfoToString(ParamStation paramStation) {
		return paramStation.getStationCode() + "," + paramStation.getStationName() + "\n";
	}
}
