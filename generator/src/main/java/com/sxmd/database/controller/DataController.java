package com.sxmd.database.controller;

import com.sxmd.database.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description: 数据请求
 *
 * @author cy
 * @date 2019年06月26日 11:12
 * Version 1.0
 */
@Controller
public class DataController {

    @Autowired
    private GeneratorService service;

    @RequestMapping("/")
    ModelAndView home() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addObject("data",service.getTableAll(null));
        return mv;
    }

    /*@RequestMapping("/generate")
    ModelAndView generate() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addObject("data",service.getTableAll(null));
        return mv;
    }*/

}
