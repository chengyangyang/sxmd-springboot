package com.sxmd.config;

import com.sxmd.database.bean.FtlEntity;
import com.sxmd.exception.SxmdException;

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
    /**
     * 基本的文件夹路径  E:\\
     */
    private static String BASE_PATH = "/file/generator";

    static {
        map.put("hollysys/entity.ftl",new FtlEntity("hollysys/entity.ftl",BASE_PATH,"{0}.java"));
        map.put("hollysys/AddModel.ftl",new FtlEntity("hollysys/AddModel.ftl",BASE_PATH,"{0}AddModel.java"));
        map.put("hollysys/EditModel.ftl",new FtlEntity("hollysys/EditModel.ftl",BASE_PATH,"{0}EditModel.java"));
        map.put("hollysys/ListModel.ftl",new FtlEntity("hollysys/ListModel.ftl",BASE_PATH,"{0}ListModel.java"));
        map.put("hollysys/Model.ftl",new FtlEntity("hollysys/Model.ftl",BASE_PATH,"{0}Model.java"));

        map.put("hollysys/dao.ftl",new FtlEntity("hollysys/dao.ftl",BASE_PATH,"{0}Dao.java"));
        map.put("hollysys/controller.ftl",new FtlEntity("hollysys/controller.ftl",BASE_PATH,"{0}Controller.java"));
        map.put("hollysys/service.ftl",new FtlEntity("hollysys/service.ftl",BASE_PATH,"{0}Service.java"));
        map.put("hollysys/serviceimpl.ftl",new FtlEntity("hollysys/serviceimpl.ftl",BASE_PATH,"{0}ServiceImpl.java"));
        map.put("hollysys/xml.ftl",new FtlEntity("hollysys/xml.ftl",BASE_PATH,"{0}Mapper.java"));
    }


    private FtlConfig() {
        throw new SxmdException("配置工具不能进行实例化");
    }

    public static FtlEntity getFtlEntity(String templateName){
        Object obj = map.get(templateName);
        if(Objects.isNull(obj)){
            throw new SxmdException("没有对应的模板");
        }
        return (FtlEntity)obj;
    }


}
