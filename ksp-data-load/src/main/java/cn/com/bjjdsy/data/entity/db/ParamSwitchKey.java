package cn.com.bjjdsy.data.entity.db;

public class ParamSwitchKey {
    private String versionCode;

    private Short switchCode;

    private Short oStationCode;

    private Short iStationCode;

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public Short getSwitchCode() {
        return switchCode;
    }

    public void setSwitchCode(Short switchCode) {
        this.switchCode = switchCode;
    }

    public Short getoStationCode() {
        return oStationCode;
    }

    public void setoStationCode(Short oStationCode) {
        this.oStationCode = oStationCode;
    }

    public Short getiStationCode() {
        return iStationCode;
    }

    public void setiStationCode(Short iStationCode) {
        this.iStationCode = iStationCode;
    }
}