package com.sxmd.mapper;

import com.sxmd.provider.BaseSelectProvider;
import org.apache.ibatis.annotations.Param;
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

    /**
     * Description:   条件单个查询
     * @author cy
     * @param obj:
     * @return T
     * @date  2019/6/21 16:00
     */
    @SelectProvider(
            type = BaseSelectProvider.class,
            method = "selectOne"
    )
    T selectOne(T obj);


    /**
     * Description:   条件查询列表 对主键进行了过滤
     * @author cy
     * @param var1:
     * @return java.util.List<T>
     * @date  2019/6/21 16:00
     */
    @SelectProvider(
            type = BaseSelectProvider.class,
            method = "select"
    )
    List<T> select(T var1);


    /**
     * Description:  条件查询数量
     * @author cy
     * @return java.util.List<T>
     * @date  2019/6/21 16:00
     */
    @SelectProvider(
            type = BaseSelectProvider.class,
            method = "selectCount"
    )
    long selectCount(T var1);

    /**
     * Description:   条件查询是否存在
     * @author cy
     * @param obj: 条件查询
     * @return T
     * @date  2019/6/21 17:40
     */
    @SelectProvider(
            type = BaseSelectProvider.class,
            method = "exists"
    )
    boolean exists(Object obj);

    /**
     * Description:   是否存在该主键
     * @author cy
     * @param id: 主键值
     * @return boolean
     * @date  2019/6/24 8:55
     */
    @SelectProvider(
            type = BaseSelectProvider.class,
            method = "existsWithPrimaryKey"
    )
    boolean existsWithPrimaryKey(@Param("id") Object id);


    /**
     * Description:   根据主键进行查询
     * @author cy
     * @param id: 主键
     * @return T
     * @date  2019/6/21 17:40
     */
    @SelectProvider(
            type = BaseSelectProvider.class,
            method = "selectByPrimaryKey"
    )
    T selectByPrimaryKey(@Param("id") Object id);

    /**
     * Description:   查询所有数据
     * @author cy
     * @return java.util.List<T>
     * @date  2019/6/21 16:00
     */
    @SelectProvider(
            type = BaseSelectProvider.class,
            method = "selectAll"
    )
    List<T> selectAll();



}
