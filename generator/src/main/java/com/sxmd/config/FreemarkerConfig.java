package com.sxmd.config;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Map;

/**
 * Description: 模板配置
 *
 * @author cy
 * @date 2019年06月26日 13:18
 * Version 1.0
 */
public class FreemarkerConfig {

    private static Configuration cfg;

    public static Configuration getConfiguration(){
        if(null == cfg){
            cfg = new Configuration(Configuration.VERSION_2_3_22);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setClassForTemplateLoading(FreemarkerConfig.class,"/templates/");
        }
        return cfg;
    }

    /**
     * Description:
     * @author cy
     * @param templateName: 模板名称
     * @param filePath: 文件生成路径
     * @param map:  数据封装
     * @return void
     * @date  2019/6/26 17:07
     */
    public static void generatorFile(String templateName,String filePath,Map<String,Object> map){
        Template template = null;
        try {
            template = getConfiguration().getTemplate(templateName);
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(filePath))));
            template.process(map,out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

}
