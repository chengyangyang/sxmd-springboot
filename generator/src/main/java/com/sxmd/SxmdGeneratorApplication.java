package com.sxmd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description: 启动类
 *
 * @author cy
 * @date 2019年06月18日 9:33
 * Version 1.0
 */

// same as @Configuration @EnableAutoConfiguration @ComponentScan
@SpringBootApplication
public class SxmdGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SxmdGeneratorApplication.class,args);
    }
}
