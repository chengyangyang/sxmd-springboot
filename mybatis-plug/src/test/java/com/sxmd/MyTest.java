package com.sxmd;

import com.sxmd.bean.SpringAnnotationBean;
import com.sxmd.mybatistest.dao.MyTestDao;
import com.sxmd.mybatistest.entity.MyTestEntity;
import com.sxmd.mybatistest.service.MyTestService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

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
    private MyTestService mytest;
    @Resource
    private MyTestDao myTestDao;

    @Test
    public void mytest(){
        MyTestEntity myTest = new MyTestEntity();
        myTest.setName("1");
        myTest.setAge("2");
        myTest.setComInfo("q");
        MyTestEntity o = this.mytest.getMytest(myTest);
        System.out.println("结果：id="+ o.getId()+"   "+ o.toString());
        Assert.assertNotNull(o);
    }

    @Test
    public void mytestList(){
        List<MyTestEntity> mytestAll = this.mytest.getMytestAll();
        Assert.assertNotNull(mytestAll);
    }

    @Test
    public void mytestListCount(){
        MyTestEntity myTest = new MyTestEntity();
        myTest.setName("1");
        long aLong = myTestDao.selectCount(myTest);
        Assert.assertNotNull(aLong);
    }

    @Test
    public void mytestById(){
        MyTestEntity myTestEntity = myTestDao.selectByPrimaryKey("1");
        Assert.assertNotNull(myTestEntity);
    }

    @Test
    public void exists(){
        MyTestEntity myTest = new MyTestEntity();
        myTest.setAge("111");
        boolean exists = myTestDao.exists(myTest);
        Assert.assertNotNull(exists);
    }



}
