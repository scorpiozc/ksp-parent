package cn.com.bjjdsy.data.service;

import java.util.List;

import cn.com.bjjdsy.data.entity.db.ParamSection;

public interface SectionService {

	public List<ParamSection> findParamSections(String versionCode);
}
