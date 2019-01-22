package cn.com.bjjdsy.common.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "custom")
public class CustomConfig {

	private String filepath;
	private int batchSize;

	public int getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	private Map<String, Integer> fakeTransferDict = new HashMap<>();

	public Map<String, Integer> getFakeTransferDict() {
		return fakeTransferDict;
	}

	public void setFakeTransferDict(Map<String, Integer> fakeTransferDict) {
		this.fakeTransferDict = fakeTransferDict;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
}
