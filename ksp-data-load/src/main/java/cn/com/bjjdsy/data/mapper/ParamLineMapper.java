package cn.com.bjjdsy.data.mapper;

import java.util.List;

import cn.com.bjjdsy.data.entity.db.ParamLine;
import cn.com.bjjdsy.data.entity.db.ParamLineKey;

public interface ParamLineMapper {
	ParamLine selectByPrimaryKey(ParamLineKey key);

	List<ParamLine> selectByVersionCode(String versionCode);
}