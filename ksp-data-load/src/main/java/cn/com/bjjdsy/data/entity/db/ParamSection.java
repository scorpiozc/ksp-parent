package cn.com.bjjdsy.data.entity.db;

public class ParamSection extends ParamSectionKey {
    private String lineCode;

    private Integer sectionDistance;

    private Integer sectionRunTime;

    private Integer startStationStopTime;

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    public Integer getSectionDistance() {
        return sectionDistance;
    }

    public void setSectionDistance(Integer sectionDistance) {
        this.sectionDistance = sectionDistance;
    }

    public Integer getSectionRunTime() {
        return sectionRunTime;
    }

    public void setSectionRunTime(Integer sectionRunTime) {
        this.sectionRunTime = sectionRunTime;
    }

    public Integer getStartStationStopTime() {
        return startStationStopTime;
    }

    public void setStartStationStopTime(Integer startStationStopTime) {
        this.startStationStopTime = startStationStopTime;
    }
}