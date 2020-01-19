package com.sxmd.content.${folderName!''}.model.em;

import com.sxmd.content.${folderName!''}.model.am.${table.tableNameToJavaName}AddModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Description: 编辑${table.tableComment!''}实体
 *
 * @author sxmd
 * @date
 * Version 1.0
 */
@Data
public class ${fileName} extends ${table.tableNameToJavaName}AddModel {

    @ApiModelProperty(name = "主键")
    @NotNull(message = "主键不能为空")
    private Long id;

}