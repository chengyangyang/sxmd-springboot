package com.sxmd.mybatistest.entity;

import lombok.Data;

import javax.persistence.Id;

/**
 * Description:
 *
 * @author cy
 * @date 2019年06月20日 14:17
 * Version 1.0
 */
@Data
public class MyTestA {

    @Id
    private String id;
}
