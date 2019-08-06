package com.sxmd.content.${folderName}.entity;
import com.sxmd.base.BaseEntity;

import lombok.Data;
import javax.persistence.Table;

import java.util.Date;

/**
* Description: ${table.tableComment!''}实体
*
* @author sxmd
* @date
* Version 1.0
*/
@Data
@Table(name = "${table.tableName}")
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
