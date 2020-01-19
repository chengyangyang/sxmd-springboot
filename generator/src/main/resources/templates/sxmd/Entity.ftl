package com.sxmd.content.${folderName!''}.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sxmd.base.BaseEntity;
import lombok.Data;


/**
 * Description: ${table.tableComment!''}实体
 *
 * @author sxmd
 * @date
 * Version 1.0
 */
@Data
@TableName("${table.tableName}")
public class ${fileName} extends BaseEntity {

    <#list columns as li>
    <#if li.columnComment??>
    /**
    * ${li.columnComment}
    */
    </#if>
    private ${li.columnTypeToJava} ${li.columnNameToJava};

    </#list>

}
