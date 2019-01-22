package cn.com.bjjdsy.data.entity.db;

public class ParamVersionRely {
    private Short id;

    private String paramCode;

    private String versionCode;

    private String paramCodeDepend;

    private String versionCodeDepend;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getParamCodeDepend() {
        return paramCodeDepend;
    }

    public void setParamCodeDepend(String paramCodeDepend) {
        this.paramCodeDepend = paramCodeDepend;
    }

    public String getVersionCodeDepend() {
        return versionCodeDepend;
    }

    public void setVersionCodeDepend(String versionCodeDepend) {
        this.versionCodeDepend = versionCodeDepend;
    }
}