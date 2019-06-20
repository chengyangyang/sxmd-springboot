package com.sxmd.helper;

import com.sxmd.bean.EntityTable;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.Set;

/**
 * Description:  拼接sql
 *
 * @author cy
 * @date 2019年06月20日 17:15
 * Version 1.0
 */
public class SqlHelper {


    /**
     * Description:   获取所有的列
     * @author cy
     * @param aClass:
     * @return java.lang.String
     * @date  2019/6/20 18:21
     */
    public static String getSqlColumns(final Class<?> aClass){
        String sqlColums = "";
        Set<String> columns = EntityHelper.getColumnNameList(aClass).getColumnNamesExcludePkIdName();
        Iterator<String> iterator = columns.iterator();
        while (iterator.hasNext()){
            sqlColums += iterator.next() + ",";
        }
        if(StringUtils.isEmpty(getSqlPkIdName(aClass))){
            sqlColums = sqlColums.substring(0,sqlColums.length() - 1);
        }else {
            sqlColums += getSqlPkIdName(aClass);
        }
        return sqlColums;
    }

    /**
     * Description:   获取表名称
     * @author cy
     * @param aClass:
     * @return java.lang.String
     * @date  2019/6/20 18:21
     */
    public static String getSqlTableName(Class<?> aClass){
        return EntityHelper.getEntityTableName(aClass);
    }

    /**
     * Description:   获取主键名称
     * @author cy
     * @param aClass:
     * @return java.lang.String
     * @date  2019/6/20 18:21
     */
    public static String getSqlPkIdName(Class<?> aClass){
        return EntityHelper.setPkIdName(aClass);
    }

}
