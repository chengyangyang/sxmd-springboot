package com.sxmd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 启动类
 *
 * @author cy
 * @date 2019年06月18日 9:33
 * Version 1.0
 */
@RestController
// same as @Configuration @EnableAutoConfiguration @ComponentScan
@SpringBootApplication
public class SxmdMybatisPlugApplication {

    public static void main(String[] args) {
        SpringApplication.run(SxmdMybatisPlugApplication.class,args);
    }

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

}
