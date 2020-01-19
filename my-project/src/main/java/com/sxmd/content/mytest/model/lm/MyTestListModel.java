package com.sxmd.content.mytest.model.lm;

import com.sxmd.content.mytest.model.em.MyTestEditModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;


/**
* Description: 测试表列表实体
*
* @author cy
* @date
* Version 1.0
*/
@Data
@ApiModel(value = "测试表列表", description = "接收参数body")
public class MyTestListModel extends MyTestEditModel {


}