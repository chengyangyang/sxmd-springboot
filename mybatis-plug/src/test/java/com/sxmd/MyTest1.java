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
import java.text.MessageFormat;
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
        //(#{list[{0}].name},#{list[{0}].comInfo},#{list[{0}].age},#{list[{0}].id}),
        String pattern1 = " (#'{'list[{0}].name'}',#'{'list[{0}].comInfo'}',#'{'list[{0}].age'}',#'{'list[{0}].id'}')";
        //使用默认的本地化对象格式化字符串
        String format = MessageFormat.format(pattern1, 1);
        System.out.println(format);

    }


}
