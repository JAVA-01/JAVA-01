package com.myproject.moods.distribute.datasourse.dao;

import com.myproject.moods.distribute.datasourse.TargetDataSource;
import com.myproject.moods.pojo.Collections;
import com.myproject.moods.pojo.CollectionsExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface testMapper {

    hh selectByName( @Param("name") String name);
    int deleteByPrimaryKey( @Param("name") String name);
    int insert(hh record);
}