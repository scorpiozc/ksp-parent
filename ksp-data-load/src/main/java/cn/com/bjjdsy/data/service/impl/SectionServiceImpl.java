package cn.com.bjjdsy.data.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.bjjdsy.data.entity.db.ParamSection;
import cn.com.bjjdsy.data.service.SectionService;

@Service
public class SectionServiceImpl implements SectionService {

	private static final Logger logger = LoggerFactory.getLogger(SectionServiceImpl.class);

	public List<ParamSection> loadParamSection(String filepath, String versionCode) {
		List<ParamSection> sections = this.findParamSections(versionCode);
		return sections;
	}

	private List<ParamSection> findParamSections(String versionCode) {
		return null;
	}

}
