package cn.com.bjjdsy.data.entity.time;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;

public class KPath {

	@JSONField(ordinal = 1, name = "O")
	private String fromStation;
	@JSONField(ordinal = 2, name = "D")
	private String toStation;
	@JSONField(serialize = false)
	private String ksn;
	@JSONField(ordinal = 3, name = "path")
	private String kpath;
	@JSONField(ordinal = 4, name = "trans")
	private String transferStation;
	@JSONField(serialize = false)
	private Map<String, TransferStation> transferStationMap;
	@JSONField(ordinal = 12, name = "subs")
	private List<SubPath> subPaths;

	// for sub path
	@JSONField(serialize = false)
	private int direct;
	@JSONField(serialize = false)
	private List<TransferStation> transferStations;
	@JSONField(serialize = false)
	private List<String> stationCodes;
	@JSONField(ordinal = 11, name = "totaltime")
	private int totalTime;
	@JSONField(ordinal = 7, name = "owalktime")
	private int oSpendTime;
	@JSONField(ordinal = 8, name = "dwalktime")
	private int dSpendTime;
	@JSONField(ordinal = 9, name = "twalktime")
	private int tSpendTime;
	@JSONField(ordinal = 10, name = "runtime")
	private int runtime;
	@JSONField(ordinal = 5, name = "begin")
	private String entryTime;
	@JSONField(ordinal = 6, name = "end")
	private String outTime;

	public KPath() {
		this.transferStations = new ArrayList<>();
		this.stationCodes = new ArrayList<>();
		this.transferStationMap = new HashMap<>();
	}

	public List<TransferStation> getTransferStations() {
		return transferStations;
	}

	public void setTransferStations(List<TransferStation> transferStations) {
		this.transferStations = transferStations;
	}

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
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

	public String getKsn() {
		return ksn;
	}

	public void setKsn(String ksn) {
		this.ksn = ksn;
	}

	public String getKpath() {
		return kpath;
	}

	public void setKpath(String kpath) {
		this.kpath = kpath;
	}

	public String getTransferStation() {
		return transferStation;
	}

	public void setTransferStation(String transferStation) {
		this.transferStation = transferStation;
	}

	public List<SubPath> getSubPaths() {
		return subPaths;
	}

	public void setSubPaths(List<SubPath> subPaths) {
		this.subPaths = subPaths;
	}

	public int getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}

	public void addTotalTime(int totalTime) {
		this.totalTime += totalTime;
	}

	public void addTSpendTime(int tSpendTime) {
		this.tSpendTime += tSpendTime;
	}

	public List<String> getStationCodes() {
		return stationCodes;
	}

	public void setStationCodes(List<String> stationCodes) {
		this.stationCodes = stationCodes;
	}

	public Map<String, TransferStation> getTransferStationMap() {
		return transferStationMap;
	}

	public void setTransferStationMap(Map<String, TransferStation> transferStationMap) {
		this.transferStationMap = transferStationMap;
	}

	public int getoSpendTime() {
		return oSpendTime;
	}

	public void setoSpendTime(int oSpendTime) {
		this.oSpendTime = oSpendTime;
	}

	public int getdSpendTime() {
		return dSpendTime;
	}

	public void setdSpendTime(int dSpendTime) {
		this.dSpendTime = dSpendTime;
	}

	public int gettSpendTime() {
		return tSpendTime;
	}

	public void settSpendTime(int tSpendTime) {
		this.tSpendTime = tSpendTime;
	}

	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public void addRuntime(int runtime) {
		this.runtime += runtime;
	}
}
