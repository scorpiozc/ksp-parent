package cn.com.bjjdsy.data.service;

import java.util.List;

import cn.com.bjjdsy.data.entity.db.ParamLine;

public interface LineService {

	List<ParamLine> findParamLines(String versionCode);
}
