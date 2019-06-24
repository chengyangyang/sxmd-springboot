package com.sxmd.mapper;

import com.sxmd.provider.BaseSelectProvider;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.SelectProvider;

/**
 * Description: 条件扩展类
 *
 * @author cy
 * @date 2019年06月24日 15:03
 * Version 1.0
 */
public interface BaseExampleMapper {

    /**
     * Description:   条件单个查询
     * @author cy
     * @param example:
     * @return T
     * @date  2019/6/21 16:00
     */
    @SelectProvider(
            type = BaseSelectProvider.class,
            method = "exampleSelectOne"
    )
    Object exampleSelectOne(ExampleMapper example);


}
