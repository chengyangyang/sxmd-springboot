package com.sxmd.content.mytest.model.am;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
* Description: 添加测试表实体
*
* @author sxmd
* @date
* Version 1.0
*/
@Data
@ApiModel(value = "添加测试表实体", description = "接收参数body")
public class MyTestAddModel {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "信息")
    private String comInfo;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;


}