package cn.com.bjjdsy.data.entity.db;

import java.util.Date;

public class ParamVersionInfo {
    private Short id;

    private String versionCode;

    private String paramCode;

    private Short versionStatue;

    private Short versionCreateType;

    private Date beginTime;

    private Date endTime;

    private String vOperator;

    private String vAuditor;

    private String vIssuer;

    private String vRemark;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    public Short getVersionStatue() {
        return versionStatue;
    }

    public void setVersionStatue(Short versionStatue) {
        this.versionStatue = versionStatue;
    }

    public Short getVersionCreateType() {
        return versionCreateType;
    }

    public void setVersionCreateType(Short versionCreateType) {
        this.versionCreateType = versionCreateType;
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

    public String getvOperator() {
        return vOperator;
    }

    public void setvOperator(String vOperator) {
        this.vOperator = vOperator;
    }

    public String getvAuditor() {
        return vAuditor;
    }

    public void setvAuditor(String vAuditor) {
        this.vAuditor = vAuditor;
    }

    public String getvIssuer() {
        return vIssuer;
    }

    public void setvIssuer(String vIssuer) {
        this.vIssuer = vIssuer;
    }

    public String getvRemark() {
        return vRemark;
    }

    public void setvRemark(String vRemark) {
        this.vRemark = vRemark;
    }
}