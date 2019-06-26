package com.sxmd.database;

import com.sxmd.database.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Description: 模板对应的配置
 *
 * @author cy
 * @date 2019年06月26日 17:19
 * Version 1.0
 */
@Service
public class FtlService {

    @Autowired
    private GeneratorService generatorService;


    public void generatorEntity(String tableName){
        HashMap<String, Object> map = new HashMap<>();
        generatorService.generatorJavaFile(tableName,"hollysys/entity.ftl",map);
    }


}
