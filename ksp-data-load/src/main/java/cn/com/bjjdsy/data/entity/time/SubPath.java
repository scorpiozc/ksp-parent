package cn.com.bjjdsy.data.entity.time;

import com.alibaba.fastjson.annotation.JSONField;

public class SubPath {

	@JSONField(ordinal = 1, name = "path")
	private String path;
	@JSONField(ordinal = 3, name = "runtime")
	private int runtime;
	@JSONField(ordinal = 4, name = "twalktime")
	private int twalktime;
	@JSONField(ordinal = 5, name = "subTotal")
	private int subTotal;
	@JSONField(serialize = false)
	private String fromStation;
	@JSONField(serialize = false)
	private String toStation;
	@JSONField(serialize = false)
	private int direct;
	@JSONField(ordinal = 2, name = "tripno")
	private String tripNo;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public int getTwalktime() {
		return twalktime;
	}

	public void setTwalktime(int twalktime) {
		this.twalktime = twalktime;
	}

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

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

	public int getSubTotal() {
		return this.runtime + this.twalktime;
	}

	public String getTripNo() {
		return tripNo;
	}

	public void setTripNo(String tripNo) {
		this.tripNo = tripNo;
	}

}
