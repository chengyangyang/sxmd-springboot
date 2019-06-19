package com.sxmd;

import com.sxmd.mybatistest.dao.MyTestDao;
import com.sxmd.mybatistest.entity.MyTestEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

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
        MyTestEntity myTest = new MyTestEntity();
        myTest.setName("cheng");
        List<MyTestEntity> o = mytest.selectOne(myTest);
        System.out.println("结果："+o);
        Assert.assertNotNull(o);
    }


}
