package com.sxmd;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Description: 启动类  @SpringBootApplication same as @Configuration @EnableAutoConfiguration @ComponentScan
 *
 * @author cy
 * @date 2019年06月18日 9:33
 * Version 1.0
 */
@SpringBootApplication
public class SxmdMyProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SxmdMyProjectApplication.class,args);
    }
}
