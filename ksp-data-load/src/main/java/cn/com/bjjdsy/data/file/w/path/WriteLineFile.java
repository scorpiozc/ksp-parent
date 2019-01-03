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
public class WriteLineFile {

	private static final Logger logger = LoggerFactory.getLogger(WriteLineFile.class);

	public void createLineBaseInfoFile(String filepath, String versionCode, List<ParamLine> lines) {
		try (FileOutputStream fos = new FileOutputStream(filepath + CalcConstant.LINE_BASE_INFO)) {
			for (ParamLine line : lines) {
				IOUtils.write(this.lineBaseInfoToString(line), fos);
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

	}

	private String lineBaseInfoToString(ParamLine paramLine) {
		return paramLine.getLineCode() + "," + paramLine.getLineName() + "\n";
	}

}
