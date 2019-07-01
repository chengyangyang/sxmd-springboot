package com.sxmd.database.controller;

import com.sxmd.database.FtlService;
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
    @Autowired
    private FtlService ftlService;

    /**
     * Description:   查询所有数据库表
     * @author cy
     * @param :
     * @return org.springframework.web.servlet.ModelAndView
     * @date  2019/7/1 18:05
     */
    @RequestMapping("/")
    ModelAndView home(String inputTableName) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addObject("inputTableName",inputTableName);
        mv.addObject("data",service.getTableAll(inputTableName));
        return mv;
    }

    /**
     * Description:   代码生成
     * @author cy
     * @param tableNameValue:
     * @return org.springframework.web.servlet.ModelAndView
     * @date  2019/7/1 18:05
     */
    @RequestMapping("/generate")
    ModelAndView generate(String tableNameValue) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("success");
        try {
            ftlService.generatorEntity(tableNameValue);
            mv.addObject("message","代码生成成功!");
        }catch (Exception e){
            e.printStackTrace();
            mv.addObject("message","代码生成失败!");
        }
        return mv;
    }

}
