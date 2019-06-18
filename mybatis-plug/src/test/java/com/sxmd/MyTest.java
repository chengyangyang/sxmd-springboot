package com.sxmd;

import com.sxmd.mybatistest.dao.MyTestDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Description:
 *
 * @author cy
 * @date 2019年06月18日 17:56
 * Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {


    @Resource
    private MyTestDao mytest;

    @Test
    public void mytest(){
        String name = mytest.getName("1");
        System.out.println("结果："+name);
        Assert.assertNotNull(name);
    }


}
