package cn.com.bjjdsy.data.service;

import java.util.List;

import cn.com.bjjdsy.data.entity.db.ParamOdRouteAccessible;
import cn.com.bjjdsy.data.entity.db.ParamOdRouteAccessibleQO;
import cn.com.bjjdsy.data.entity.db.ParamOdRouteEffective;

public interface ParamOdRouteAccessibleService {

	void saveParamOdRouteAccessible(ParamOdRouteAccessible paramOdRouteAccessible);

	void saveParamOdRouteAccessible(List<ParamOdRouteAccessible> paramOdRouteAccessibles);

	void saveBatcheParamOdRouteAccessible(List<ParamOdRouteAccessible> paramOdRouteAccessibles);

	int findParamOdRouteAccessibleByVersionCode(String versionCode);

	List<ParamOdRouteEffective> findParamOdRouteAccessible(ParamOdRouteAccessibleQO qo);
}
