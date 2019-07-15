package com.sxmd;

import com.sxmd.config.FreemarkerConfig;
import com.sxmd.database.FtlService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Description:
 *
 * @author cy
 * @date 2019年06月26日 15:18
 * Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {
    @Autowired
    FtlService service;

    @Test
    public void gen(){
        service.generatorEntity("my_test");
    }
}
