package com.sxmd.content.${folderName!''}.model.dm;

import com.sxmd.content.${folderName!''}.entity.${table.tableNameToJavaName}Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description: ${table.tableComment!''}详情实体
 *
 * @author sxmd
 * @date
 * Version 1.0
 */
@Data
@ApiModel(value = "${table.tableComment!''}详情实体")
public class ${fileName} extends ${table.tableNameToJavaName}Entity {


}