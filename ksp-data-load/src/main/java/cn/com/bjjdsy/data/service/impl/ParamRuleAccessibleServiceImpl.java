package cn.com.bjjdsy.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.bjjdsy.data.entity.db.ParamRuleAccessible;
import cn.com.bjjdsy.data.mapper.ParamRuleAccessibleMapper;
import cn.com.bjjdsy.data.service.ParamRuleAccessibleService;

@Service
public class ParamRuleAccessibleServiceImpl implements ParamRuleAccessibleService {

	@Autowired
	private ParamRuleAccessibleMapper paramRuleAccessibleMapper;

	@Override
	public ParamRuleAccessible findParamRuleAccessible(String versionCode) {
		return paramRuleAccessibleMapper.selectByPrimaryKey(versionCode);
	}

}
