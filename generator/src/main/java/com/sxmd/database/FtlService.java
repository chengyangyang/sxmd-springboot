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
        HashMap<String, Object> map = new HashMap<>(10);
        generatorService.generatorJavaFile(true,tableName,"hollysys/AddModel.ftl",map);
        generatorService.generatorJavaFile(true,tableName,"hollysys/EditModel.ftl",map);
        generatorService.generatorJavaFile(true,tableName,"hollysys/ListModel.ftl",map);
        generatorService.generatorJavaFile(true,tableName,"hollysys/Model.ftl",map);
        generatorService.generatorJavaFile(true,tableName,"hollysys/entity.ftl",map);

        generatorService.generatorJavaFile(true,tableName,"hollysys/dao.ftl",map);
        generatorService.generatorJavaFile(true,tableName,"hollysys/controller.ftl",map);
        generatorService.generatorJavaFile(true,tableName,"hollysys/service.ftl",map);
        generatorService.generatorJavaFile(true,tableName,"hollysys/serviceimpl.ftl",map);
        generatorService.generatorJavaFile(true,tableName,"hollysys/xml.ftl",map);

    }


}
