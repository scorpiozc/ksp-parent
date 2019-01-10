package cn.com.bjjdsy.data.service;

import cn.com.bjjdsy.data.entity.db.ParamVersionInfo;

public interface ParamVersionInfoService {

	public ParamVersionInfo getParamVersionInfoByTaskJobId(String taskJobId);
}
