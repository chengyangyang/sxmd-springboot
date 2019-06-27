package com.hollysys.smartfactory.account.system.model.dm;

import lombok.Data;
import javax.persistence.Table;

/**
* Description: ${table.tableComment!''}实体
*
* @author sxmd
* @date
* Version 1.0
*/
@Data
@Table(name = "${table.tableName}")
public class ${fileName} extends BaseModel {


    <#list columns as li>
    <#if li.columnComment??>
    // ${li.columnComment}
    </#if>
    private ${li.columnTypeToJava} ${li.columnNameToJava};

    </#list>

}
