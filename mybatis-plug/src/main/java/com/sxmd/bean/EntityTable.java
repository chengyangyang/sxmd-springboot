package com.sxmd.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Description: 表的实体类
 *
 * @author cy
 * @date 2019年06月19日 17:56
 * Version 1.0
 */
@Data
@Accessors(chain = true)
public class EntityTable {

    /**
     * 表名称
     */
    private String tableSqlName;
    /**
     * 主键名称
     */
    private String pkIdName;
    /**
     * 主键名称
     */
    private String pkIdSqlName;
    /**
     * 列名称集合（key 是字段名称   value 是数据库字段数据）
     */
    private Map<String,String> columnExcludePkId;

}
