package com.sxmd.content;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * Description:  springboot 测试
 *
 * @author cy
 * @date 2019年12月19日 15:50
 * Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Main {


    @Autowired
    private CassandraTemplate cassandraTemplate;


    @Test
    public void mytestInsert(){
        // 如果存在 就进行更新
        String cql = "insert into my_test(id,name,age,des,status) values('1','曹操',23,'宁教我负天下人1','曹阿蛮')";
        String cql1 = "insert into my_test(id,name,age,des,status) values('1','曹操',1,'勿叫天下人负我2','曹阿瞒')";
        String cql2 = "insert into my_test(id,name,age,des,status) values('1','曹操',57,'勿叫天下人负我3','曹阿瞒')";
        String cql3 = "insert into my_test(id,name,age,des,status) values('1','曹操',11,'勿叫天下人负我4','曹阿瞒')";
        String cql4 = "insert into my_test(id,name,age,des,status) values('1','曹操',16,'勿叫天下人负我5','曹阿瞒')";
        boolean result = cassandraTemplate.getCqlOperations().execute(cql);
        boolean result1 = cassandraTemplate.getCqlOperations().execute(cql1);
        boolean result2 = cassandraTemplate.getCqlOperations().execute(cql2);
        boolean result3 = cassandraTemplate.getCqlOperations().execute(cql3);
        boolean result4 = cassandraTemplate.getCqlOperations().execute(cql4);
        Assert.assertTrue(result);
    }

    @Test
    public void mytestUpdate(){
        String cql = "update my_test set age = null where id = '1'";
        boolean result = cassandraTemplate.getCqlOperations().execute(cql);
        Assert.assertTrue(result);
    }

    @Test
    public void mytestDelete(){
        String cql = "delete from my_test where id = '1'";
        boolean result = cassandraTemplate.getCqlOperations().execute(cql);
        Assert.assertTrue(result);
    }

    @Test
    public void mytestSelect(){
        String cql = "select * from my_test where id = '1'";
        List<Map<String, Object>> result = cassandraTemplate.getCqlOperations().queryForList(cql);
        Assert.assertNotNull(result);
    }

}
