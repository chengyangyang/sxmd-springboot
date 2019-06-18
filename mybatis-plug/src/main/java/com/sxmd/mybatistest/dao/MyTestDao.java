package com.sxmd.mybatistest.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * Description:
 *
 * @author cy
 * @date 2019年06月18日 17:50
 * Version 1.0
 */
@Mapper
public interface MyTestDao {

    public String getName(String id);
}
