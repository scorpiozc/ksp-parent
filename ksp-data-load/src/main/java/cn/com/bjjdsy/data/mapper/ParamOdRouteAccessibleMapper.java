package cn.com.bjjdsy.data.mapper;

import java.util.List;

import cn.com.bjjdsy.data.entity.db.ParamOdRouteAccessible;

public interface ParamOdRouteAccessibleMapper {
	int insert(ParamOdRouteAccessible record);

	int insertSelective(ParamOdRouteAccessible record);

	int insertBatch(List<ParamOdRouteAccessible> records);
}