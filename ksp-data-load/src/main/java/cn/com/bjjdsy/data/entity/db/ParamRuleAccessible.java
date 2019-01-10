package cn.com.bjjdsy.data.entity.db;

import java.math.BigDecimal;

public class ParamRuleAccessible {
    private String versionCode;

    private Short kNum;

    private BigDecimal transCoeff;

    private BigDecimal norStaDevia;

    private BigDecimal depInteRatio;

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public Short getkNum() {
        return kNum;
    }

    public void setkNum(Short kNum) {
        this.kNum = kNum;
    }

    public BigDecimal getTransCoeff() {
        return transCoeff;
    }

    public void setTransCoeff(BigDecimal transCoeff) {
        this.transCoeff = transCoeff;
    }

    public BigDecimal getNorStaDevia() {
        return norStaDevia;
    }

    public void setNorStaDevia(BigDecimal norStaDevia) {
        this.norStaDevia = norStaDevia;
    }

    public BigDecimal getDepInteRatio() {
        return depInteRatio;
    }

    public void setDepInteRatio(BigDecimal depInteRatio) {
        this.depInteRatio = depInteRatio;
    }
}