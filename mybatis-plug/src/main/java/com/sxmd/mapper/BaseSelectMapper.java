package com.sxmd.mapper;

import com.sxmd.mybatistest.entity.MyTestEntity;
import com.sxmd.provider.BaseSelectProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Description: 基本查询
 *
 * @author cy
 * @date 2019年06月19日 10:58
 * Version 1.0
 */
public interface BaseSelectMapper<T> {

    @SelectProvider(
            type = BaseSelectProvider.class,
            method = "selectOne"
    )
    List<MyTestEntity> selectOne(MyTestEntity myTestEntity);

}
