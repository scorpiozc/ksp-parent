package cn.com.bjjdsy.data.entity.db;

public class ParamStation extends ParamStationKey {
    private String lineCode;

    private String tccStationCode;

    private String stationName;

    private Long stationErgCode;

    private Short downSequence;

    private Short isShareLine;

    private Short stationType;

    private Short stationModality;

    private Integer xCoordinate;

    private Integer yCoordinate;

    private Short switchCode;

    private Short isUseState;

    private Short isCaOdRoute;

    private String remark;

    private String tccLineCode;

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    public String getTccStationCode() {
        return tccStationCode;
    }

    public void setTccStationCode(String tccStationCode) {
        this.tccStationCode = tccStationCode;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Long getStationErgCode() {
        return stationErgCode;
    }

    public void setStationErgCode(Long stationErgCode) {
        this.stationErgCode = stationErgCode;
    }

    public Short getDownSequence() {
        return downSequence;
    }

    public void setDownSequence(Short downSequence) {
        this.downSequence = downSequence;
    }

    public Short getIsShareLine() {
        return isShareLine;
    }

    public void setIsShareLine(Short isShareLine) {
        this.isShareLine = isShareLine;
    }

    public Short getStationType() {
        return stationType;
    }

    public void setStationType(Short stationType) {
        this.stationType = stationType;
    }

    public Short getStationModality() {
        return stationModality;
    }

    public void setStationModality(Short stationModality) {
        this.stationModality = stationModality;
    }

    public Integer getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(Integer xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public Integer getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(Integer yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public Short getSwitchCode() {
        return switchCode;
    }

    public void setSwitchCode(Short switchCode) {
        this.switchCode = switchCode;
    }

    public Short getIsUseState() {
        return isUseState;
    }

    public void setIsUseState(Short isUseState) {
        this.isUseState = isUseState;
    }

    public Short getIsCaOdRoute() {
        return isCaOdRoute;
    }

    public void setIsCaOdRoute(Short isCaOdRoute) {
        this.isCaOdRoute = isCaOdRoute;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTccLineCode() {
        return tccLineCode;
    }

    public void setTccLineCode(String tccLineCode) {
        this.tccLineCode = tccLineCode;
    }
}