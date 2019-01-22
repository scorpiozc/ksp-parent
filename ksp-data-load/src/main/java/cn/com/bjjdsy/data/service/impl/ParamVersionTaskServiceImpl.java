package cn.com.bjjdsy.data.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.bjjdsy.data.entity.db.ParamVersionTask;
import cn.com.bjjdsy.data.mapper.ParamVersionTaskMapper;
import cn.com.bjjdsy.data.service.ParamVersionTaskService;

@Service
public class ParamVersionTaskServiceImpl implements ParamVersionTaskService {

	@Autowired
	private ParamVersionTaskMapper paramVersionTaskMapper;

	@Override
	public String getVersionCode(int taskJobId) {
		return "01";
	}

	@Override
	public ParamVersionTask getParamVersionTaskByTaskJobId(String taskJobId) {
		return paramVersionTaskMapper.selectByPrimaryKey(Short.parseShort(taskJobId));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateParamVersionInfoStatueToExecute(ParamVersionTask task) {
		task.setTaskJobStatue(Short.parseShort("1"));
		task.setBeginTime(new Date());
		paramVersionTaskMapper.updateByPrimaryKeySelective(task);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateParamVersionInfoStatueToComplete(ParamVersionTask task) {
		task.setTaskJobStatue(Short.parseShort("2"));
		task.setEndTime(new Date());
		paramVersionTaskMapper.updateByPrimaryKeySelective(task);
	}

	private void updateParamVersionInfoStatue(ParamVersionTask task) {
		paramVersionTaskMapper.updateByPrimaryKeySelective(task);
	}
}
