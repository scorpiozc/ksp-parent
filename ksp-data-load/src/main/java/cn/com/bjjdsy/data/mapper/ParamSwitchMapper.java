package cn.com.bjjdsy.data.mapper;

import java.util.List;

import cn.com.bjjdsy.data.entity.db.ParamSwitch;
import cn.com.bjjdsy.data.entity.db.ParamSwitchKey;

public interface ParamSwitchMapper {
	ParamSwitch selectByPrimaryKey(ParamSwitchKey key);

	List<ParamSwitch> selectByVersionCode(String versionCode);
}