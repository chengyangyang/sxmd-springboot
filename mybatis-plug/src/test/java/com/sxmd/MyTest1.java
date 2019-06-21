package com.sxmd;

import com.sxmd.helper.EntityHelper;
import com.sxmd.mybatistest.entity.MyTestEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Description:
 *
 * @author cy
 * @date 2019年06月21日 10:13
 * Version 1.0
 */

public class MyTest1 {

    public static void main(String[] args) {

            IntStream.of(new int[] {1, 2, 3}).forEach(System.out::println);
            IntStream.range(1, 3).forEach(System.out::println);
            IntStream.rangeClosed(1, 3).forEach(System.out::println);


    }


}
