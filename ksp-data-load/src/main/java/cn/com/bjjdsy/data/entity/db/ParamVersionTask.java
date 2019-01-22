package cn.com.bjjdsy.data.entity.db;

import java.util.Date;

public class ParamVersionTask {
    private Short taskJobId;

    private String versionCode;

    private Short taskJobStatue;

    private Date beginTime;

    private Date endTime;

    private String taskInfo;

    private Date caBeginTime;

    private Date caEndTime;

    public Short getTaskJobId() {
        return taskJobId;
    }

    public void setTaskJobId(Short taskJobId) {
        this.taskJobId = taskJobId;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public Short getTaskJobStatue() {
        return taskJobStatue;
    }

    public void setTaskJobStatue(Short taskJobStatue) {
        this.taskJobStatue = taskJobStatue;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(String taskInfo) {
        this.taskInfo = taskInfo;
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