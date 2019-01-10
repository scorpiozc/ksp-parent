package cn.com.bjjdsy.data.entity.db;

public class ParamSectionKey {
    private String versionCode;

    private Short oStationCode;

    private Short dStationCode;

    private Short direction;

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public Short getoStationCode() {
        return oStationCode;
    }

    public void setoStationCode(Short oStationCode) {
        this.oStationCode = oStationCode;
    }

    public Short getdStationCode() {
        return dStationCode;
    }

    public void setdStationCode(Short dStationCode) {
        this.dStationCode = dStationCode;
    }

    public Short getDirection() {
        return direction;
    }

    public void setDirection(Short direction) {
        this.direction = direction;
    }
}