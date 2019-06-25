package com.sxmd;

import lombok.Data;

/**
 * Description:
 *
 * @author cy
 * @date 2019年06月25日 14:39
 * Version 1.0
 */
@Data
public class MyTest2 {

    private int age;

    private String name;

    public MyTest2(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
