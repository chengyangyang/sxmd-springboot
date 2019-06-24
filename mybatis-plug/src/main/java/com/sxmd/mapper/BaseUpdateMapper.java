package com.sxmd.mapper;

import com.sxmd.provider.BaseUpdateProvider;
import org.apache.ibatis.annotations.UpdateProvider;

/**
 * Description: 更新接口
 *
 * @author cy
 * @date 2019年06月24日 13:37
 * Version 1.0
 */
public interface BaseUpdateMapper<T> {

    /**
     * Description:   单个更新
     * @author cy
     * @param obj:
     * @return int
     * @date  2019/6/24 13:42
     */
    @UpdateProvider(
            type = BaseUpdateProvider.class,
            method = "updateByPrimaryKey"
    )
    int updateByPrimaryKey(T obj);
}
