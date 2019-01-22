package cn.com.bjjdsy.calc.entity;

public class ReachPath {

	private String fromStation;
	private String toStation;
	private String sn;
	private String pathLine;
	private String pathStation;
	private String pathTransfer;
	private String time;
	private String inpedance;

	public String getFromStation() {
		return fromStation;
	}

	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}

	public String getToStation() {
		return toStation;
	}

	public void setToStation(String toStation) {
		this.toStation = toStation;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getPathLine() {
		return pathLine;
	}

	public void setPathLine(String pathLine) {
		this.pathLine = pathLine;
	}

	public String getPathStation() {
		return pathStation;
	}

	public void setPathStation(String pathStation) {
		this.pathStation = pathStation;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getInpedance() {
		return inpedance;
	}

	public void setInpedance(String inpedance) {
		this.inpedance = inpedance;
	}

	public String getPathTransfer() {
		return pathTransfer;
	}

	public void setPathTransfer(String pathTransfer) {
		this.pathTransfer = pathTransfer;
	}

	@Override
	public String toString() {
		return this.fromStation + "," + this.toStation + "," + this.sn + "," + this.pathLine + "," + this.pathStation
				+ "," + this.time + "," + this.inpedance + "," + this.pathTransfer;
	}
}
