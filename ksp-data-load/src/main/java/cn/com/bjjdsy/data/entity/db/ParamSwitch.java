package cn.com.bjjdsy.data.entity.db;

public class ParamSwitch extends ParamSwitchKey {
    private String oLineCode;

    private String iLineCode;

    private Short switchWalkTime;

    public String getoLineCode() {
        return oLineCode;
    }

    public void setoLineCode(String oLineCode) {
        this.oLineCode = oLineCode;
    }

    public String getiLineCode() {
        return iLineCode;
    }

    public void setiLineCode(String iLineCode) {
        this.iLineCode = iLineCode;
    }

    public Short getSwitchWalkTime() {
        return switchWalkTime;
    }

    public void setSwitchWalkTime(Short switchWalkTime) {
        this.switchWalkTime = switchWalkTime;
    }
}