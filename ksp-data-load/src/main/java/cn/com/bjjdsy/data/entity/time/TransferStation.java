package cn.com.bjjdsy.data.entity.time;

public class TransferStation {

	private String fromStation;
	private String toStation;
	private int fromDirect;
	private int toDirect;

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

}
