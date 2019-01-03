package cn.com.bjjdsy.data.service;

import java.util.List;

import cn.com.bjjdsy.data.entity.db.ParamSwitch;

public interface SwitchService {

	List<ParamSwitch> loadParamSwitch(String filepath, String versionCode);
}
