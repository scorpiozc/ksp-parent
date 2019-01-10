package cn.com.bjjdsy.data.mapper;

import cn.com.bjjdsy.data.entity.db.ParamVersionTask;

public interface ParamVersionTaskMapper {
    ParamVersionTask selectByPrimaryKey(Short taskJobId);

    int updateByPrimaryKeySelective(ParamVersionTask record);

    int updateByPrimaryKey(ParamVersionTask record);
}