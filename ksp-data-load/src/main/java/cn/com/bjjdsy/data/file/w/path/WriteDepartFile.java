package cn.com.bjjdsy.data.file.w.path;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.bjjdsy.common.constant.CalcConstant;
import cn.com.bjjdsy.data.entity.db.ParamLine;

@Service
public class WriteDepartFile {

	private static final Logger logger = LoggerFactory.getLogger(WriteDepartFile.class);

	public void createLineDepartIntervalTimeFile(String filepath, String versionCode, List<ParamLine> lines) {
		try (FileOutputStream fos = new FileOutputStream(filepath + CalcConstant.LINE_DEPART_INTERVAL_TIME)) {
			for (ParamLine line : lines) {
				IOUtils.write(this.lineDepartIntervalTimeToString(line), fos);
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private String lineDepartIntervalTimeToString(ParamLine paramLine) {
		return paramLine.getLineCode() + "," + "1," + paramLine.getUpPeriodInterval() + "\n";
	}
}
