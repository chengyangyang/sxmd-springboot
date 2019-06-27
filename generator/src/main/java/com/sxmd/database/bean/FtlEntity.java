package com.sxmd.database.bean;

import lombok.Data;

/**
 * Description:
 *
 * @author cy
 * @date 2019年06月26日 17:22
 * Version 1.0
 */
@Data
public class FtlEntity {

    // ftl 模板名称    从templates 下算起    如 /holl/a.ftl
    private String ftlName;
    //  文件夹路径
    private String createFilePath;
    //  文件名称{0} 表示 用表转化后的名称进行替换
    private String createFileName = "{0}.java";

    public FtlEntity(String ftlName, String createFilePath,String createFileName) {
        this.ftlName = ftlName;
        this.createFilePath = createFilePath;
        this.createFileName = createFileName;
    }

    public FtlEntity(String ftlName, String createFilePath) {
        this.ftlName = ftlName;
        this.createFilePath = createFilePath;
    }
}
