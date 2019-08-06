package com.sxmd.config;

import com.sxmd.help.SqlToJavaHelp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Description: 在spring 启动后执行的数据
 *
 * @author cy
 * @date 2019年07月31日 19:17
 * Version 1.0
 */
@Component
@Slf4j
public class SpringAfterDate implements ApplicationRunner {

    @Value("${sxmd.zip-download-folder-name}")
    private String folderName;

    @Value("#{${sxmd.sql-type-to-java-type}}")
    private Map<String,String> map;

    @Value("#{'${sxmd.filter-entity}'.split(',')}")
    private List list;

    @Value("${sxmd.table-name-to-java-name-strategy}")
    private String strategy;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        SqlToJavaHelp.initMapAndListData(this.map,this.list,strategy);
        FtlConfig.initZipDate(folderName);
        log.debug("=============项目启动完成=============");
    }
}
