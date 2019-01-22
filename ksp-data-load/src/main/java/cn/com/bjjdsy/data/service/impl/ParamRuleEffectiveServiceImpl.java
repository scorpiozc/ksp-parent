package cn.com.bjjdsy.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.bjjdsy.data.entity.db.ParamRuleEffective;
import cn.com.bjjdsy.data.mapper.ParamRuleEffectiveMapper;
import cn.com.bjjdsy.data.service.ParamRuleEffectiveService;

@Service
public class ParamRuleEffectiveServiceImpl implements ParamRuleEffectiveService {

	@Autowired
	private ParamRuleEffectiveMapper paramRuleEffectiveMapper;

	@Override
	public ParamRuleEffective getParamRuleEffective(String versionCode) {
		return paramRuleEffectiveMapper.selectByPrimaryKey(versionCode);
	}

}
