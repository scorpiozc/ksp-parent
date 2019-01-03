package cn.com.bjjdsy.data.entity.time;

public class RunMapKey implements Comparable<RunMapKey> {

	private String depTime;
	private String tripNo;
	private String lineNo;

	public String getLineNo() {
		return lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	public String getDepTime() {
		return depTime;
	}

	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}

	public String getTripNo() {
		return tripNo;
	}

	public void setTripNo(String tripNo) {
		this.tripNo = tripNo;
	}

	@Override
	public int compareTo(RunMapKey o) {
		return (int) (Long.parseLong(this.getDepTime()) - Long.parseLong(o.getDepTime()));
	}

}
