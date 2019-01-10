package cn.com.bjjdsy.data.mapper;

import java.util.List;

import cn.com.bjjdsy.data.entity.db.ParamLine;
import cn.com.bjjdsy.data.entity.db.ParamStation;
import cn.com.bjjdsy.data.entity.db.ParamStationKey;

public interface ParamStationMapper {
    ParamStation selectByPrimaryKey(ParamStationKey key);
    
    List<ParamStation> selectByVersionCode(String versionCode);
}