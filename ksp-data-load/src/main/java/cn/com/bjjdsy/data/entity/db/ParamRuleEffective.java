package cn.com.bjjdsy.data.entity.db;

import java.math.BigDecimal;

public class ParamRuleEffective {
    private String versionCode;

    private String kNum;

    private BigDecimal impePropLimit;

    private Short impeValueLimit;

    private BigDecimal norStaDevia;

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getkNum() {
        return kNum;
    }

    public void setkNum(String kNum) {
        this.kNum = kNum;
    }

    public BigDecimal getImpePropLimit() {
        return impePropLimit;
    }

    public void setImpePropLimit(BigDecimal impePropLimit) {
        this.impePropLimit = impePropLimit;
    }

    public Short getImpeValueLimit() {
        return impeValueLimit;
    }

    public void setImpeValueLimit(Short impeValueLimit) {
        this.impeValueLimit = impeValueLimit;
    }

    public BigDecimal getNorStaDevia() {
        return norStaDevia;
    }

    public void setNorStaDevia(BigDecimal norStaDevia) {
        this.norStaDevia = norStaDevia;
    }
}