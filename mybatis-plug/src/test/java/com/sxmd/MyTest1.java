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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;
import java.util.function.Predicate;
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


    public static MyTest2 getV(MyTest2 a, Predicate<MyTest2> pre,Consumer<MyTest2> con){
        if(pre.test(a)){
            con.accept(a);
        }
        return a;
    }


    public static void main(String[] args) {
        MyTest2 a = new MyTest2(12,"ce");
        System.out.println(getV(a,x-> x.getAge() > 11,x->{
            System.out.println("函数式编程方法");
            x.setAge(13);
        }).toString());
        System.out.println(getV(a,x-> x.getAge() > 12,x->x.setAge(13)).toString());
    }

}
