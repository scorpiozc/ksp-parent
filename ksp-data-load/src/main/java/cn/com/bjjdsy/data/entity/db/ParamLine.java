package cn.com.bjjdsy.data.entity.db;

public class ParamLine {

	private String versionCode;
	private String lineCode;
	private String lineName;
	private int upLowInterval;
	private int downLowInterval;
	private int upPeriodInterval;
	private int downPeriodInterval;

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getLineCode() {
		return lineCode;
	}

	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public int getUpLowInterval() {
		return upLowInterval;
	}

	public void setUpLowInterval(int upLowInterval) {
		this.upLowInterval = upLowInterval;
	}

	public int getDownLowInterval() {
		return downLowInterval;
	}

	public void setDownLowInterval(int downLowInterval) {
		this.downLowInterval = downLowInterval;
	}

	@Override
	public String toString() {
		return this.lineCode + "," + this.lineName;
	}

	public int getUpPeriodInterval() {
		return upPeriodInterval;
	}

	public void setUpPeriodInterval(int upPeriodInterval) {
		this.upPeriodInterval = upPeriodInterval;
	}

	public int getDownPeriodInterval() {
		return downPeriodInterval;
	}

	public void setDownPeriodInterval(int downPeriodInterval) {
		this.downPeriodInterval = downPeriodInterval;
	}
}
