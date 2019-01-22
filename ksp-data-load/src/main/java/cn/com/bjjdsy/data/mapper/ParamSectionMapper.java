package cn.com.bjjdsy.data.mapper;

import java.util.List;

import cn.com.bjjdsy.data.entity.db.ParamSection;
import cn.com.bjjdsy.data.entity.db.ParamSectionKey;

public interface ParamSectionMapper {
    ParamSection selectByPrimaryKey(ParamSectionKey key);
    
    List<ParamSection> selectByVersionCode(String versionCode);
}