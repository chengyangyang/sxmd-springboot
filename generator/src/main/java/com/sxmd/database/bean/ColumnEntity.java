package com.sxmd.database.bean;

import lombok.Data;

/**
 * Description: 列定义
 *
 * @author cy
 * @date 2019年06月26日 12:46
 * Version 1.0
 */
@Data
public class ColumnEntity {

    /**
     *  列名称
     */
    private String columnName;

    /**
     *  列类型
     */
    private String columnType;

    /**
     *  列注释
     */
    private String columnComment;

    /**
     *  是否为空
     */
    private boolean isNull;

    /**
     *  -------------------------------
     *  列名称转化Java名称
     */
    private String columnNameToJava;

    /**
     *  列类型 转化成Java名称
     */
    private String columnTypeToJava;

}
