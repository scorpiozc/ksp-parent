package cn.com.bjjdsy.data.service;

import java.util.List;

import cn.com.bjjdsy.data.entity.db.ParamVersionRely;

public interface ParamVersionRelyService {

	List<ParamVersionRely> getParamVersionRelyByVersionCode(String versionCode);
}
