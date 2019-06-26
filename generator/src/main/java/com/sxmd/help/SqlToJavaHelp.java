package com.sxmd.help;

import com.sxmd.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author cy
 * @date 2019年06月26日 15:59
 * Version 1.0
 */
public class SqlToJavaHelp {

    private static final Logger log = LoggerFactory.getLogger(SqlToJavaHelp.class);

    private static Map map = new HashMap<String,String>();

    static {
        map.put("varchar","String");
        map.put("timestamp","Date");
        map.put("bool","boolean");
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
        if(StringUtils.isBlank(str)){
            str = sqlType;
            log.error("没有找到对应的Java类型！");
        }
        return str;
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
        String substring = tableName.substring(tableName.indexOf("_"), tableName.length());
        // 驼峰式转化 并首字母大写
        String capitalize = StringUtils.capitalize(substring);
        return StringUtils.camelCaseName(capitalize);
    }

}
