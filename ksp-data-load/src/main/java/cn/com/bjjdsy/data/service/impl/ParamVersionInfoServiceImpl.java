package cn.com.bjjdsy.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.bjjdsy.data.entity.db.ParamVersionInfo;
import cn.com.bjjdsy.data.mapper.ParamVersionInfoMapper;
import cn.com.bjjdsy.data.service.ParamVersionInfoService;

@Service
public class ParamVersionInfoServiceImpl implements ParamVersionInfoService {

	@Autowired
	private ParamVersionInfoMapper paramVersionInfoMapper;

	@Override
	public ParamVersionInfo getParamVersionInfoByTaskJobId(String taskJobId) {
		return paramVersionInfoMapper.selectByTaskJobId(Short.parseShort(taskJobId));
	}

}
