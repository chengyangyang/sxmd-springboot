package com.sxmd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description: 启动类  @SpringBootApplication same as @Configuration @EnableAutoConfiguration @ComponentScan
 *
 * @author cy
 * @date 2019年06月18日 9:33
 * Version 1.0
 */
@SpringBootApplication
public class SxmdGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SxmdGeneratorApplication.class,args);
    }
}
