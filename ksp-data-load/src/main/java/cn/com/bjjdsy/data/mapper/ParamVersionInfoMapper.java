package cn.com.bjjdsy.data.mapper;

import cn.com.bjjdsy.data.entity.db.ParamVersionInfo;

public interface ParamVersionInfoMapper {
	ParamVersionInfo selectByPrimaryKey(Short id);

	ParamVersionInfo selectByTaskJobId(Short taskJobId);
}