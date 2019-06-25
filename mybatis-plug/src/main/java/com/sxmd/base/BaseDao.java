package com.sxmd.base;

import com.sxmd.mapper.*;

/**
 * Description: 通用接口
 *
 * @author cy
 * @date 2019年06月24日 17:34
 * Version 1.0
 */
public interface BaseDao<T> extends BaseSelectMapper<T>, BaseInsertMapper<T>, BaseUpdateMapper<T>, BaseDeleteMapper<T>, BaseExampleMapper<T> {

}
