package cn.com.bjjdsy.data.service;

import cn.com.bjjdsy.data.entity.db.ParamVersionInfo;

public interface ParamVersionInfoService {

	ParamVersionInfo getParamVersionInfoByTaskJobId(String taskJobId);

}
