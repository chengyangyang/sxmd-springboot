package com.sxmd;

import com.sxmd.base.Example;
import com.sxmd.mybatistest.dao.MyTestDao;
import com.sxmd.mybatistest.entity.MyTestEntity;
import com.sxmd.mybatistest.service.MyTestService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;

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

    @Test
    public void existsById(){

        boolean exists = myTestDao.existsWithPrimaryKey("11111");
        Assert.assertNotNull(exists);
    }

    @Test
    public void insert(){
        MyTestEntity myTest = new MyTestEntity();
        myTest.setId("12");
        myTest.setComInfo("ee");
        myTest.setName("新增测试");
        myTest.setAge("123");
        int insert = myTestDao.insert(myTest);
        Assert.assertNotNull(insert);
    }

    @Test
    public void insertAll(){
        ArrayList<MyTestEntity> myTestEntities = new ArrayList<>();
        MyTestEntity myTest = new MyTestEntity();
        myTest.setId("12");
        myTest.setComInfo("ee1");
        myTest.setName("新增测试1");
        myTest.setAge("1");
        myTestEntities.add(myTest);

        MyTestEntity myTest1 = new MyTestEntity();
        myTest1.setId("13");
        myTest1.setComInfo("ee2");
        myTest1.setName("新增测试2");
        myTest1.setAge("2");
        myTestEntities.add(myTest1);
        int insert = myTestDao.insertAll(myTestEntities);
        Assert.assertNotNull(insert);
    }

    @Test
    public void updateById(){
        MyTestEntity myTest = new MyTestEntity();
        myTest.setId("11");
        myTest.setComInfo("ee12");
        myTest.setName("新增测试12");
        myTest.setAge("112");
        int insert = myTestDao.updateByPrimaryKey(myTest);
        Assert.assertNotNull(insert);
    }

    @Test
    public void deleteByPrimaryKey(){
        MyTestEntity myTest = new MyTestEntity();
        myTest.setId("11");
        myTest.setComInfo("ee12");
        myTest.setName("新增测试12");
        myTest.setAge("112");
        int insert = myTestDao.deleteByPrimaryKey("11");
        Assert.assertNotNull(insert);
    }

    @Test
    public void deleteByPrimaryKeys(){
        MyTestEntity myTest = new MyTestEntity();
        myTest.setId("11");
        myTest.setComInfo("ee12");
        myTest.setName("新增测试12");
        myTest.setAge("112");
        int insert = myTestDao.deleteByPrimaryKeys(Arrays.asList("1","12"));
        Assert.assertNotNull(insert);
    }

    @Test
    public void exampleSelectOne(){
        Example exampleMapper = new Example().andEqualTo("name","1");
           Object o = myTestDao.exampleSelectOne(exampleMapper);
        Assert.assertNotNull(o);
    }

    @Test
    public void exampleSelectList(){
        Example exampleMapper = new Example().andEqualTo("name","1");
        Object o = myTestDao.exampleSelectList(exampleMapper);
        Assert.assertNotNull(o);
    }

}
