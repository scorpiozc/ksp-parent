package cn.com.bjjdsy.data.entity.db;

public class ParamLine extends ParamLineKey {
    private String lineName;

    private String tccLineCode;

    private String tccLineName;

    private Long lineErgCode;

    private Short lineType;

    private Integer lineLength;

    private String lineOperationUnit;

    private String lineColor;

    private Short upPeriodInterval;

    private Short downPeriodInterval;

    private Short upLowInterval;

    private Short downLowInterval;

    private String remark;

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getTccLineCode() {
        return tccLineCode;
    }

    public void setTccLineCode(String tccLineCode) {
        this.tccLineCode = tccLineCode;
    }

    public String getTccLineName() {
        return tccLineName;
    }

    public void setTccLineName(String tccLineName) {
        this.tccLineName = tccLineName;
    }

    public Long getLineErgCode() {
        return lineErgCode;
    }

    public void setLineErgCode(Long lineErgCode) {
        this.lineErgCode = lineErgCode;
    }

    public Short getLineType() {
        return lineType;
    }

    public void setLineType(Short lineType) {
        this.lineType = lineType;
    }

    public Integer getLineLength() {
        return lineLength;
    }

    public void setLineLength(Integer lineLength) {
        this.lineLength = lineLength;
    }

    public String getLineOperationUnit() {
        return lineOperationUnit;
    }

    public void setLineOperationUnit(String lineOperationUnit) {
        this.lineOperationUnit = lineOperationUnit;
    }

    public String getLineColor() {
        return lineColor;
    }

    public void setLineColor(String lineColor) {
        this.lineColor = lineColor;
    }

    public Short getUpPeriodInterval() {
        return upPeriodInterval;
    }

    public void setUpPeriodInterval(Short upPeriodInterval) {
        this.upPeriodInterval = upPeriodInterval;
    }

    public Short getDownPeriodInterval() {
        return downPeriodInterval;
    }

    public void setDownPeriodInterval(Short downPeriodInterval) {
        this.downPeriodInterval = downPeriodInterval;
    }

    public Short getUpLowInterval() {
        return upLowInterval;
    }

    public void setUpLowInterval(Short upLowInterval) {
        this.upLowInterval = upLowInterval;
    }

    public Short getDownLowInterval() {
        return downLowInterval;
    }

    public void setDownLowInterval(Short downLowInterval) {
        this.downLowInterval = downLowInterval;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}