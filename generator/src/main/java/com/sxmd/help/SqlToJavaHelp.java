package com.sxmd.help;

import com.sxmd.exception.SxmdException;
import com.sxmd.utils.StringUtil;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: sql 转化java
 *
 * @author cy
 * @date 2019年06月26日 15:59
 * Version 1.0
 */
@Slf4j
public class SqlToJavaHelp {

    private static Map map = new HashMap<String,String>();

    private static List list = new ArrayList<String>();

    static {
        // 类型转化
        map.put("varchar","String");
        map.put("timestamp","Date");
        map.put("bool","boolean");
        map.put("int4","Integer");


        // 字段过滤
        list.add("cs");
        list.add("pkid");
        list.add("ct");
        list.add("cu");
        list.add("mt");
        list.add("mu");
        list.add("cd");
        list.add("co");
    }

    private SqlToJavaHelp(){
        throw new SxmdException("工具类不能被实例化");
    }

    /**
     * Description:   实体列的类型转化
     * @author cy
     * @param sqlType:
     * @return java.lang.String
     * @date  2019/6/26 17:02
     */
    public static String getJavaTypeBySqlType(String sqlType){
        String str = (String)map.get(sqlType);
        if(StringUtil.isBlank(str)){
            str = sqlType;
            log.error("没有找到对应的Java类型！"+ sqlType);
        }
        return str;
    }

    /**
     * Description:   对该字段是否进行过滤
     * @author cy
     * @param sqlColumnsName:
     * @return boolean
     * @date  2019/6/27 9:04
     */
    public static boolean filterSqlColumns(String sqlColumnsName){
        return list.contains(sqlColumnsName);
    }

    /**
     * Description:   得到类的名称
     * @author cy
     * @param tableName:
     * @return java.lang.String
     * @date  2019/6/26 16:56
     */
    public static String classNameByTable(String tableName){
        // 截取第一个下划线前面的部分
        String substring = tableName.substring(tableName.indexOf('_'), tableName.length());
        // 驼峰式转化 并首字母大写
        String capitalize = StringUtil.capitalize(substring);
        return StringUtil.camelCaseName(capitalize);
    }

}
