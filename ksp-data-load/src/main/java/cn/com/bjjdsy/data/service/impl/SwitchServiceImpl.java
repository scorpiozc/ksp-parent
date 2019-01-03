package cn.com.bjjdsy.data.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.bjjdsy.data.entity.db.ParamSwitch;
import cn.com.bjjdsy.data.service.SwitchService;

@Service
public class SwitchServiceImpl implements SwitchService{

	private static final Logger logger = LoggerFactory.getLogger(SwitchServiceImpl.class);

	public List<ParamSwitch> loadParamSwitch(String filepath, String versionCode) {
		List<ParamSwitch> switchs = this.findParamSwitchs(versionCode);
		return switchs;
	}

	private List<ParamSwitch> findParamSwitchs(String versionCode) {
		return null;
	}

}
