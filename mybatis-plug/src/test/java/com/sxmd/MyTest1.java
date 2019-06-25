package com.sxmd;

import com.sxmd.helper.EntityHelper;
import com.sxmd.mybatistest.entity.MyTestEntity;
import com.sxmd.mybatistest.service.MyTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Description:
 *
 * @author cy
 * @date 2019年06月21日 10:13
 * Version 1.0
 */

public class MyTest1 {



    public static void main(String[] args) {
        List<MyTest2> list = Arrays.asList(
                new MyTest2(5, "a"),
                new MyTest2(12, "b"),
                new MyTest2(11, "b"),
                new MyTest2(8, "c")
        );

        String reduce = list.stream().map(x -> x.getName() + ",").reduce("1",String::concat);
        System.out.println(reduce);


    }


}
