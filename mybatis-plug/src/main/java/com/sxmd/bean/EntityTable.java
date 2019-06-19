package com.sxmd.bean;

import lombok.Data;

import java.util.LinkedHashSet;

/**
 * Description: 表的实体类
 *
 * @author cy
 * @date 2019年06月19日 17:56
 * Version 1.0
 */
@Data
public class EntityTable {

    // 表名称
    private String tableName;
    // 主键名称
    private String pkIdName;
    // 列名称集合（驼峰式转化成了_式）
    private LinkedHashSet<String> columnName;

}
