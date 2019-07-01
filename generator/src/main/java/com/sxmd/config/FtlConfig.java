package com.sxmd.config;

import com.sxmd.database.bean.FtlEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Description: 每个ftl 文件都对应一个配置
 *
 * @author cy
 * @date 2019年06月26日 17:21
 * Version 1.0
 */
public class FtlConfig {

    private static Map map = new HashMap<String, FtlEntity>();

    static {
        map.put("hollysys/entity.ftl",new FtlEntity("hollysys/entity.ftl","E:\\","{0}.java"));
        map.put("hollysys/AddModel.ftl",new FtlEntity("hollysys/AddModel.ftl","E:\\","{0}AddModel.java"));
        map.put("hollysys/EditModel.ftl",new FtlEntity("hollysys/EditModel.ftl","E:\\","{0}EditModel.java"));
        map.put("hollysys/ListModel.ftl",new FtlEntity("hollysys/ListModel.ftl","E:\\","{0}ListModel.java"));
        map.put("hollysys/Model.ftl",new FtlEntity("hollysys/Model.ftl","E:\\","{0}Model.java"));

        map.put("hollysys/dao.ftl",new FtlEntity("hollysys/dao.ftl","E:\\","{0}Dao.java"));
        map.put("hollysys/controller.ftl",new FtlEntity("hollysys/controller.ftl","E:\\","{0}Controller.java"));
        map.put("hollysys/service.ftl",new FtlEntity("hollysys/service.ftl","E:\\","{0}Service.java"));
        map.put("hollysys/serviceimpl.ftl",new FtlEntity("hollysys/serviceimpl.ftl","E:\\","{0}ServiceImpl.java"));
        map.put("hollysys/xml.ftl",new FtlEntity("hollysys/xml.ftl","E:\\","{0}Mapper.java"));
    }

    public static FtlEntity getFtlEntity(String templateName){
        Object obj = map.get(templateName);
        if(Objects.isNull(obj)){
            throw new RuntimeException("没有对应的模板");
        }
        return (FtlEntity)obj;
    }


}
