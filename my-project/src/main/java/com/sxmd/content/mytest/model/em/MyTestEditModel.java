package com.sxmd.content.mytest.model.em;

import com.sxmd.content.mytest.model.am.MyTestAddModel;
import lombok.Data;

/**
* Description: 编辑测试表实体
*
* @author sxmd
* @date
* Version 1.0
*/
@Data
public class MyTestEditModel extends MyTestAddModel {

    private Long id;

    private String pkid;

    private String cs;
}