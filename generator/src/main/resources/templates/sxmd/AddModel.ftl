package com.sxmd.content.${folderName!''}.model.am;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Description: 添加${table.tableComment!''}实体
 *
 * @author sxmd
 * @date
 * Version 1.0
 */
@Data
@ApiModel(value = "新增${table.tableComment!''}实体", description = "接收参数body")
public class ${fileName} {

    <#list columns as li>
    @ApiModelProperty(value = "${(li.columnComment)!}")
    <#if li.columnTypeToJava = 'Date'>
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    </#if>
    private ${li.columnTypeToJava} ${li.columnNameToJava};

    </#list>

}