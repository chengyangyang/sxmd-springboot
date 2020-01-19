package com.sxmd.content.mytest.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sxmd.base.BaseEntity;
import lombok.Data;

/**
 * Description: 测试表实体
 *
 * @author sxmd
 * @date 1212
 * Version 1.0
 */
@Data
@TableName("my_test1")
public class MyTestEntity extends BaseEntity {

    /**
     * 年龄
     */
    private String name;

    // 年龄
    private Integer age;

    // 信息
    private String comInfo;


}
