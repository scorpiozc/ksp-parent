package cn.com.bjjdsy.data.file.w.path;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.bjjdsy.common.constant.CalcConstant;
import cn.com.bjjdsy.data.entity.db.ParamSwitch;

@Service
public class WriteTransferWalktimeFile {

	private static final Logger logger = LoggerFactory.getLogger(WriteTransferWalktimeFile.class);

	public void createTransferWalktimeFile(String filepath, String versionCode, List<ParamSwitch> switchs) {
		try (FileOutputStream fos = new FileOutputStream(filepath + CalcConstant.TRANSFER_LINE_WALKTIME_INFO)) {
			for (ParamSwitch pswitch : switchs) {
				IOUtils.write(this.transferWalktimeToString(pswitch), fos);
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private String transferWalktimeToString(ParamSwitch paramSwitch) {
		return paramSwitch.getSwitchCode() + "," + paramSwitch.getoLineCode() + "," + paramSwitch.getiLineCode() + ","
				+ paramSwitch.getSwitchWalkTime() + "\n";
	}
}
