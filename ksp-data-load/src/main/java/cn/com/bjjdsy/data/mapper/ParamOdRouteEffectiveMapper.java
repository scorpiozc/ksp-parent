package cn.com.bjjdsy.data.mapper;

import cn.com.bjjdsy.data.entity.db.ParamOdRouteEffective;
import cn.com.bjjdsy.data.entity.db.ParamOdRouteEffectiveKey;

public interface ParamOdRouteEffectiveMapper {
	int insert(ParamOdRouteEffective record);

	int insertSelective(ParamOdRouteEffective record);

	ParamOdRouteEffective selectByPrimaryKey(ParamOdRouteEffectiveKey key);

	int selectParamOdRouteEffectiveByVersionCode(String versionCode);
}