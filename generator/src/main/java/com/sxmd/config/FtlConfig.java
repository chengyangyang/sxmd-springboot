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
    }

    public static FtlEntity getFtlEntity(String templateName){
        Object obj = map.get(templateName);
        if(Objects.isNull(obj)){
            throw new RuntimeException("没有对应的模板");
        }
        return (FtlEntity)obj;
    }


}
