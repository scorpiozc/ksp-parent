package cn.com.bjjdsy.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.bjjdsy.data.entity.db.ParamVersionRely;
import cn.com.bjjdsy.data.mapper.ParamVersionRelyMapper;
import cn.com.bjjdsy.data.service.ParamVersionRelyService;

@Service
public class ParamVersionRelyServiceImpl implements ParamVersionRelyService {

	@Autowired
	private ParamVersionRelyMapper paramVersionRelyMapper;

	@Override
	public List<ParamVersionRely> getParamVersionRelyByVersionCode(String versionCode) {
		return paramVersionRelyMapper.selectByVersionCode(versionCode);
	}

}
