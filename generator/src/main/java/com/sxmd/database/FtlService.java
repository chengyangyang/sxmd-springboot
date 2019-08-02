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


    /**
     * Description: zip 需要生成的模板
     * @author cy
     * @param tableName:
     * @return void
     * @date  2019/8/2 16:05
     */
    public void generatorEntity(String tableName){
        HashMap<String, Object> map = new HashMap<>(10);
        generatorService.generatorJavaFile(true,tableName,"sxmd/AddModel.ftl",map);
        generatorService.generatorJavaFile(true,tableName,"sxmd/EditModel.ftl",map);
        generatorService.generatorJavaFile(true,tableName,"sxmd/ListModel.ftl",map);
        generatorService.generatorJavaFile(true,tableName,"sxmd/InfoModel.ftl",map);
        generatorService.generatorJavaFile(true,tableName,"sxmd/Entity.ftl",map);

        generatorService.generatorJavaFile(true,tableName,"sxmd/Dao.ftl",map);
        generatorService.generatorJavaFile(true,tableName,"sxmd/Controller.ftl",map);
        generatorService.generatorJavaFile(true,tableName,"sxmd/Service.ftl",map);
        generatorService.generatorJavaFile(true,tableName,"sxmd/Serviceimpl.ftl",map);
        generatorService.generatorJavaFile(true,tableName,"sxmd/Xml.ftl",map);

    }


}
