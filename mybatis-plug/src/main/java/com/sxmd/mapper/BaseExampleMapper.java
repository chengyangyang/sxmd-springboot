package com.sxmd.mapper;

import com.sxmd.base.Example;
import com.sxmd.provider.BaseSelectProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Description: 条件扩展类
 *
 * @author cy
 * @date 2019年06月24日 15:03
 * Version 1.0
 */
public interface BaseExampleMapper<T> {

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
    T exampleSelectOne(Example example);

    /**
     * Description:   条件查询（列表）
     * @author cy
     * @param example:
     * @return T
     * @date  2019/6/21 16:00
     */
    @SelectProvider(
            type = BaseSelectProvider.class,
            method = "exampleSelectList"
    )
    List<T> exampleSelectList(Example example);


}
