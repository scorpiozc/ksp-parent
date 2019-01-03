package cn.com.bjjdsy.data.entity.db;

public class ParamSwitch {

	private String versionCode;
	private int switchCode;
	private String oLineCode;
	private int oStationCode;
	private String iLineCode;
	private int iStationCode;
	private int switchWalkTime;

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public int getSwitchCode() {
		return switchCode;
	}

	public void setSwitchCode(int switchCode) {
		this.switchCode = switchCode;
	}

	public String getoLineCode() {
		return oLineCode;
	}

	public void setoLineCode(String oLineCode) {
		this.oLineCode = oLineCode;
	}

	public int getoStationCode() {
		return oStationCode;
	}

	public void setoStationCode(int oStationCode) {
		this.oStationCode = oStationCode;
	}

	public String getiLineCode() {
		return iLineCode;
	}

	public void setiLineCode(String iLineCode) {
		this.iLineCode = iLineCode;
	}

	public int getiStationCode() {
		return iStationCode;
	}

	public void setiStationCode(int iStationCode) {
		this.iStationCode = iStationCode;
	}

	public int getSwitchWalkTime() {
		return switchWalkTime;
	}

	public void setSwitchWalkTime(int switchWalkTime) {
		this.switchWalkTime = switchWalkTime;
	}

}
