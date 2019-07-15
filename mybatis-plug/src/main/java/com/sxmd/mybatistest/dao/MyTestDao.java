package com.sxmd.mybatistest.dao;

import com.sxmd.mapper.*;
import com.sxmd.mybatistest.entity.MyTestEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * Description: 测试
 *
 * @author cy
 * @date 2019年06月18日 17:50
 * Version 1.0
 */
@Mapper
public interface MyTestDao extends BaseSelectMapper<MyTestEntity>, BaseInsertMapper<MyTestEntity>, BaseUpdateMapper<MyTestEntity> , BaseDeleteMapper<MyTestEntity> , BaseExampleMapper<MyTestEntity> {

    /**
     * Description:   测试接口
     * @author cy
     * @param id:
     * @return java.lang.String
     * @date  2019/7/15 11:31
     */
    public String getName(String id);
}
