package cn.com.bjjdsy.data.entity.time;

public class AccseWalkTime {

	private String fromAccStationCode;
	private String toAccStationCode;
	private int dateType;
	private int timeAttr;
	private int fromDirect;
	private int toDirect;
	private int quick;
	private int middle;
	private int slow;
	private String versionId;

	public String getFromAccStationCode() {
		return fromAccStationCode;
	}

	public void setFromAccStationCode(String fromAccStationCode) {
		this.fromAccStationCode = fromAccStationCode;
	}

	public String getToAccStationCode() {
		return toAccStationCode;
	}

	public void setToAccStationCode(String toAccStationCode) {
		this.toAccStationCode = toAccStationCode;
	}

	public int getDateType() {
		return dateType;
	}

	public void setDateType(int dateType) {
		this.dateType = dateType;
	}

	public int getTimeAttr() {
		return timeAttr;
	}

	public void setTimeAttr(int timeAttr) {
		this.timeAttr = timeAttr;
	}

	public int getFromDirect() {
		return fromDirect;
	}

	public void setFromDirect(int fromDirect) {
		this.fromDirect = fromDirect;
	}

	public int getToDirect() {
		return toDirect;
	}

	public void setToDirect(int toDirect) {
		this.toDirect = toDirect;
	}

	public int getQuick() {
		return quick;
	}

	public void setQuick(int quick) {
		this.quick = quick;
	}

	public int getMiddle() {
		return middle;
	}

	public void setMiddle(int middle) {
		this.middle = middle;
	}

	public int getSlow() {
		return slow;
	}

	public void setSlow(int slow) {
		this.slow = slow;
	}

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

}
