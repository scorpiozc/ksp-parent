package cn.com.bjjdsy.data.mapper;

import java.util.List;

import cn.com.bjjdsy.data.entity.db.ParamOdRouteAccessible;
import cn.com.bjjdsy.data.entity.db.ParamOdRouteAccessibleQO;
import cn.com.bjjdsy.data.entity.db.ParamOdRouteEffective;

public interface ParamOdRouteAccessibleMapper {
	int insert(ParamOdRouteAccessible record);

	int insertSelective(ParamOdRouteAccessible record);

	int insertBatch(List<ParamOdRouteAccessible> records);

	int selectParamOdRouteAccessibleByVersionCode(String versionCode);

	List<ParamOdRouteEffective> selectParamOdRouteAccessible(ParamOdRouteAccessibleQO qo);
}