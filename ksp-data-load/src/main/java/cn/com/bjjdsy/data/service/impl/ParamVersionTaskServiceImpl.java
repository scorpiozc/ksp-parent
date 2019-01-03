package cn.com.bjjdsy.data.service.impl;

import org.springframework.stereotype.Service;

import cn.com.bjjdsy.data.service.ParamVersionTaskService;

@Service
public class ParamVersionTaskServiceImpl implements ParamVersionTaskService {

	@Override
	public String getVersionCode(int taskJobId) {
		return "01";
	}

}
