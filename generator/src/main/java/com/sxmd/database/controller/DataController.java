package com.sxmd.database.controller;

import com.sxmd.config.FtlConfig;
import com.sxmd.database.FtlService;
import com.sxmd.database.service.GeneratorService;
import com.sxmd.utils.FileUtil;
import com.sxmd.utils.ZipUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Description: 数据请求
 *
 * @author cy
 * @date 2019年06月26日 11:12
 * Version 1.0
 */
@Controller
@Slf4j
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
    @RequestMapping("/generate11")
    ModelAndView generate(String tableNameValue) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("success");
        try {
            ftlService.generatorEntity(tableNameValue);
            mv.addObject("message","代码生成成功!");
        }catch (Exception e){
            log.error("context",e);
            mv.addObject("message","代码生成失败!");
        }
        return mv;
    }

    /**
     * Description:   代码生成打包zip
     * @author cy
     * @param tableNameValue: 表名称
     * @param response:
     * @return void
     * @date  2019/8/2 9:08
     */
    @RequestMapping("/generate")
    public synchronized void downloadZip(@RequestParam("tableNameValue") String tableNameValue, HttpServletResponse response){
        // 调用文件生成   默认生成到FtlConfig 加载路径下
        ftlService.generatorEntity(tableNameValue);
        // 进行文件夹打包
        String downloadZipPath = FtlConfig.DOWNLOAD_ZIP_PATH;
        String path = FtlConfig.class.getResource("/").getPath();
        String filePath = path + "a123.zip";
        ZipUtil.createZip(downloadZipPath,filePath,true);
        FileUtil.downloadFile(filePath,"生成代码.zip",response);
        // 下载完成后进行删除
        FileUtil.deleteFile(filePath);
        try {
            FileUtil.deleteDirectory(new File(downloadZipPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.debug("完成下载");
    }

}
