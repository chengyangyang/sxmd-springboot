package com.sxmd.content.mytest.model.em;

import com.sxmd.content.mytest.model.am.MyTestAddModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
* Description: 编辑测试表实体
*
* @author sxmd
* @date
* Version 1.0
*/
@Data
@ApiModel
public class MyTestEditModel extends MyTestAddModel {

    @ApiModelProperty(name = "主键")
    @NotNull(message = "主键不能为空")
    private Long id;

}