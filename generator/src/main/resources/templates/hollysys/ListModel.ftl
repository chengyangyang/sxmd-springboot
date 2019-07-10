package com.hollysys.smartfactory.account.system.model.vm;

import com.hollysys.smartfactory.account.system.model.vr.${table.tableNameToJavaName}EditModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
* Description: ${table.tableComment!''}列表实体
*
* @author cy
* @date
* Version 1.0
*/
@Data
@ApiModel(value = "${table.tableComment!''}列表", description = "接收参数body")
public class ${fileName} extends ${table.tableNameToJavaName}EditModel {

}