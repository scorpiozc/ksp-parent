package cn.com.bjjdsy.data.service;

import java.util.List;

import cn.com.bjjdsy.data.entity.db.ParamOdRouteEffective;

public interface ParamOdRouteEffectiveService {

	int findParamOdRouteEffectiveByVersionCode(String versionCode);

	void saveBatchParamOdRouteEffective(String versionCode, List<ParamOdRouteEffective> paramOdRouteEffectives);
}
