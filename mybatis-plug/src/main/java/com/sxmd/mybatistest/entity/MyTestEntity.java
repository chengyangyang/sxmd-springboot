package com.sxmd.mybatistest.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Description:
 *
 * @author cy
 * @date 2019年06月19日 15:57
 * Version 1.0
 */
@Data
@Table(name = "my_test")
public class MyTestEntity extends MyTestA {

    @Id
    private String id;
    private String name;


}
