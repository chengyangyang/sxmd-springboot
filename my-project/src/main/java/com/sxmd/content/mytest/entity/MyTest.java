package com.sxmd.content.mytest.entity;

import com.sxmd.base.BaseEntity;
import lombok.Data;
import javax.persistence.Table;
import java.util.Date;

/**
* Description: 测试表实体
*
* @author sxmd
* @date
* Version 1.0
*/
@Data
@Table(name = "my_test")
public class MyTest extends BaseEntity {


    /**
     * 年龄
     */
    private String name;

    // 年龄
    private Integer age;

    // 信息
    private String comInfo;

    // 更新时间
    private Date updateDate;


}
