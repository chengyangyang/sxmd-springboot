package com.sxmd.mapper;

import com.sxmd.provider.BaseInsertProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description: 通用插入接口
 *
 * @author cy
 * @date 2019年06月24日 9:22
 * Version 1.0
 */
public interface BaseInsertMapper<T> {

    /**
     * Description:   插入新的数据
     * @author cy
     * @param obj:
     * @return int
     * @date  2019/6/24 9:28
     */
    @InsertProvider(
            type = BaseInsertProvider.class,
            method = "insert"
    )
    int insert(T obj);

    /**
     * Description:   批量插入新的数据
     * @author cy
     * @param list:
     * @return int
     * @date  2019/6/24 9:28
     */
    @InsertProvider(
            type = BaseInsertProvider.class,
            method = "insertAll"
    )
    int insertAll(@Param("list") List<T> list);


}
