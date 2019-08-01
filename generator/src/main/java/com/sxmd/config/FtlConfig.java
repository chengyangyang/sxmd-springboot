package com.sxmd.config;

import com.sxmd.database.bean.FtlEntity;
import com.sxmd.exception.SxmdException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
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


    private FtlConfig() {
        throw new SxmdException("配置工具不能进行实例化");
    }


    /**
     * Description:   项目启动后初始化数据 这里面文件下载后会进行删除
     * @author cy
     * @param zipFolderName: zip 包里面的文件夹名称
     * @return void
     * @date  2019/7/31 19:39
     */
    public static void initZipDate(String zipFolderName){
        String path = FtlConfig.class.getResource("/").getPath();
        path = path + File.separator + zipFolderName + File.separator;
        map.put("sxmd/Entity.ftl",new FtlEntity("sxmd/Entity.ftl",path + "entity","{0}.java"));
        map.put("sxmd/AddModel.ftl",new FtlEntity("sxmd/AddModel.ftl",path +"model" + File.separator + "am","{0}AddModel.java"));
        map.put("sxmd/EditModel.ftl",new FtlEntity("sxmd/EditModel.ftl",path +"model" + File.separator + "em","{0}EditModel.java"));
        map.put("sxmd/ListModel.ftl",new FtlEntity("sxmd/ListModel.ftl",path +"model" + File.separator + "lm","{0}ListModel.java"));
        map.put("sxmd/InfoModel.ftl",new FtlEntity("sxmd/InfoModel.ftl",path +"model" + File.separator + "dm","{0}Model.java"));

        map.put("sxmd/Dao.ftl",new FtlEntity("sxmd/Dao.ftl",path + "dao","{0}Dao.java"));
        map.put("sxmd/Controller.ftl",new FtlEntity("sxmd/Controller.ftl",path + "controller","{0}Controller.java"));
        map.put("sxmd/Service.ftl",new FtlEntity("sxmd/Service.ftl",path + "service","{0}Service.java"));
        map.put("sxmd/Serviceimpl.ftl",new FtlEntity("sxmd/Serviceimpl.ftl",path + "service","{0}ServiceImpl.java"));
        map.put("sxmd/Xml.ftl",new FtlEntity("sxmd/Xml.ftl",path + "xml","{0}Mapper.java"));
    }

    public static FtlEntity getFtlEntity(String templateName){
        Object obj = map.get(templateName);
        if(Objects.isNull(obj)){
            throw new SxmdException("没有对应的模板");
        }
        return (FtlEntity)obj;
    }


}
