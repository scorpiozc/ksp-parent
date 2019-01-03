package cn.com.bjjdsy.data.entity.db;

public class ParamSection {

	private String versionCode;
	private String lineCode;
	private int oStationCode;
	private int dStationCode;
	private int direction;
	private int sectionDistance;
	private int sectionRunTime;
	private int startStationStopTime;

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

	public int getoStationCode() {
		return oStationCode;
	}

	public void setoStationCode(int oStationCode) {
		this.oStationCode = oStationCode;
	}

	public int getdStationCode() {
		return dStationCode;
	}

	public void setdStationCode(int dStationCode) {
		this.dStationCode = dStationCode;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getSectionRunTime() {
		return sectionRunTime;
	}

	public void setSectionRunTime(int sectionRunTime) {
		this.sectionRunTime = sectionRunTime;
	}

	public int getStartStationStopTime() {
		return startStationStopTime;
	}

	public void setStartStationStopTime(int startStationStopTime) {
		this.startStationStopTime = startStationStopTime;
	}

	public int getSectionDistance() {
		return sectionDistance;
	}

	public void setSectionDistance(int sectionDistance) {
		this.sectionDistance = sectionDistance;
	}

	@Override
	public String toString() {
		return this.lineCode + "," + this.oStationCode + "," + this.dStationCode + "," + this.sectionDistance + ","
				+ this.sectionRunTime + "," + this.direction;
	}
}
