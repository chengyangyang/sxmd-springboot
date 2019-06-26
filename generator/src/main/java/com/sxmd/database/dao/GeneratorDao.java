package com.sxmd.database.dao;

import com.sxmd.database.bean.ColumnEntity;
import com.sxmd.database.bean.TableEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description: 通用接口
 *
 * @author cy
 * @date 2019年06月26日 10:24
 * Version 1.0
 */
public interface GeneratorDao {

    List<TableEntity> getAllTable(@Param("tableName") String tableName);

    TableEntity getOneTable(@Param("tableName") String tableName);

    List<ColumnEntity> getColumnsByTable(@Param("tableName") String tableName);

}
