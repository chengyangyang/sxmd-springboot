package com.sxmd.mapper;

import com.sxmd.provider.BaseDeleteProvider;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description: 删除接口
 *
 * @author cy
 * @date 2019年06月24日 14:07
 * Version 1.0
 */
public interface BaseDeleteMapper<T> {

    /**
     * Description:   通过主键单个删除
     * @author cy
     * @param id:
     * @return int
     * @date  2019/6/24 14:32
     */
    @DeleteProvider(
            type = BaseDeleteProvider.class,
            method = "deleteByPrimaryKey"
    )
    int deleteByPrimaryKey(@Param("id") Object id);

    /**
     * Description:   通过主键批量删除
     * @author cy
     * @param ids: 主键集合
     * @return int
     * @date  2019/6/24 14:32
     */
    @DeleteProvider(
            type = BaseDeleteProvider.class,
            method = "deleteByPrimaryKeys"
    )
    int deleteByPrimaryKeys(@Param("ids") List<Object> ids);

}
