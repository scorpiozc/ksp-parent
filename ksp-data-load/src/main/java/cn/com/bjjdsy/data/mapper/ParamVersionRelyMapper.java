package cn.com.bjjdsy.data.mapper;

import java.util.List;

import cn.com.bjjdsy.data.entity.db.ParamVersionRely;

public interface ParamVersionRelyMapper {
	ParamVersionRely selectByPrimaryKey(Short id);

	List<ParamVersionRely> selectByVersionCode(String versionCode);
}