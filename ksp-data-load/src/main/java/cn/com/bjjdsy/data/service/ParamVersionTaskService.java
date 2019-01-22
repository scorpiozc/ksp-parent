package cn.com.bjjdsy.data.service;

import cn.com.bjjdsy.data.entity.db.ParamVersionTask;

public interface ParamVersionTaskService {

	String getVersionCode(int taskJobId);

	ParamVersionTask getParamVersionTaskByTaskJobId(String taskJobId);

	void updateParamVersionInfoStatueToExecute(ParamVersionTask task);

	void updateParamVersionInfoStatueToComplete(ParamVersionTask task);
}
