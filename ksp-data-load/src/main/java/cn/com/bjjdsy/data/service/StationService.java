package cn.com.bjjdsy.data.service;

import java.util.List;

import cn.com.bjjdsy.data.entity.db.ParamStation;

public interface StationService {

	public List<ParamStation> loadParamStation(String filepath, String versionCode);
}
