package cn.com.bjjdsy.data.entity.db;

public class ParamOdRouteEffective extends ParamOdRouteEffectiveKey {
    private Short oStationCode;

    private Short dStationCode;

    private Short routeNo;

    private String routeLineCode;

    private String routeStationCode;

    private String routeStationTime;

    private Long routeDistance;

    private Long routeStationNo;

    private Integer costTime;

    private Integer impedanceTime;

    private String routeTransferCode;

    private String routeTransferTime;

    private Short routeTransferFrequency;

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

    public Short getRouteNo() {
        return routeNo;
    }

    public void setRouteNo(Short routeNo) {
        this.routeNo = routeNo;
    }

    public String getRouteLineCode() {
        return routeLineCode;
    }

    public void setRouteLineCode(String routeLineCode) {
        this.routeLineCode = routeLineCode;
    }

    public String getRouteStationCode() {
        return routeStationCode;
    }

    public void setRouteStationCode(String routeStationCode) {
        this.routeStationCode = routeStationCode;
    }

    public String getRouteStationTime() {
        return routeStationTime;
    }

    public void setRouteStationTime(String routeStationTime) {
        this.routeStationTime = routeStationTime;
    }

    public Long getRouteDistance() {
        return routeDistance;
    }

    public void setRouteDistance(Long routeDistance) {
        this.routeDistance = routeDistance;
    }

    public Long getRouteStationNo() {
        return routeStationNo;
    }

    public void setRouteStationNo(Long routeStationNo) {
        this.routeStationNo = routeStationNo;
    }

    public Integer getCostTime() {
        return costTime;
    }

    public void setCostTime(Integer costTime) {
        this.costTime = costTime;
    }

    public Integer getImpedanceTime() {
        return impedanceTime;
    }

    public void setImpedanceTime(Integer impedanceTime) {
        this.impedanceTime = impedanceTime;
    }

    public String getRouteTransferCode() {
        return routeTransferCode;
    }

    public void setRouteTransferCode(String routeTransferCode) {
        this.routeTransferCode = routeTransferCode;
    }

    public String getRouteTransferTime() {
        return routeTransferTime;
    }

    public void setRouteTransferTime(String routeTransferTime) {
        this.routeTransferTime = routeTransferTime;
    }

    public Short getRouteTransferFrequency() {
        return routeTransferFrequency;
    }

    public void setRouteTransferFrequency(Short routeTransferFrequency) {
        this.routeTransferFrequency = routeTransferFrequency;
    }
}