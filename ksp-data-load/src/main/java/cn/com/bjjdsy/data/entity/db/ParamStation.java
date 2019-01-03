package cn.com.bjjdsy.data.entity.db;

public class ParamStation {

	private String versionCode;
	private String lineCode;
	private int stationCode;
	private String stationName;
	private String tccStationCode;
	private int switchCode;

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

	public int getStationCode() {
		return stationCode;
	}

	public void setStationCode(int stationCode) {
		this.stationCode = stationCode;
	}

	public String getTccStationCode() {
		return tccStationCode;
	}

	public void setTccStationCode(String tccStationCode) {
		this.tccStationCode = tccStationCode;
	}

	public int getSwitchCode() {
		return switchCode;
	}

	public void setSwitchCode(int switchCode) {
		this.switchCode = switchCode;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	@Override
	public String toString() {
		return this.stationCode + "," + this.stationName+"\n123";
	}
}
