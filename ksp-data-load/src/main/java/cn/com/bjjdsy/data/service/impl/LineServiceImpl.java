package cn.com.bjjdsy.data.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.bjjdsy.data.entity.db.ParamLine;
import cn.com.bjjdsy.data.service.LineService;

@Service
public class LineServiceImpl implements LineService {

	private static final Logger logger = LoggerFactory.getLogger(LineServiceImpl.class);

	public List<ParamLine> loadParamLine(String filepath, String versionCode) {
		List<ParamLine> lines = this.findParamLines(versionCode);
		return lines;
	}

	private List<ParamLine> findParamLines(String versionCode) {
		return null;
	}

}
