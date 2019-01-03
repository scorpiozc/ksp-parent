package cn.com.bjjdsy.data.entity.db;

import java.util.Date;

public class ParamVersionTask {

	private Integer taskJobId;
	private String versionCode;
	private Integer taskJobStatue;
	private Date caBeginTime;
	private Date caEndTime;

	public Integer getTaskJobId() {
		return taskJobId;
	}

	public void setTaskJobId(Integer taskJobId) {
		this.taskJobId = taskJobId;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public Integer getTaskJobStatue() {
		return taskJobStatue;
	}

	public void setTaskJobStatue(Integer taskJobStatue) {
		this.taskJobStatue = taskJobStatue;
	}

	public Date getCaBeginTime() {
		return caBeginTime;
	}

	public void setCaBeginTime(Date caBeginTime) {
		this.caBeginTime = caBeginTime;
	}

	public Date getCaEndTime() {
		return caEndTime;
	}

	public void setCaEndTime(Date caEndTime) {
		this.caEndTime = caEndTime;
	}

}
