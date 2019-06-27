package com.sxmd.database.bean;

import lombok.Data;

import java.util.Date;

/**
 * Description: 从数据库中获取表信息
 *
 * @author cy
 * @date 2019年06月26日 10:20
 * Version 1.0
 */
@Data
public class TableEntity {

    // 数据库表名称
    private String tableName;

    // 引擎名称
    private String engine;

    // 表注释
    private String tableComment;

    // 创建时间
    private Date createTime;

    //--------------------------------
    // 表名转化后的名称 也就是类的名称
    private String tableNameToJavaName;

}
