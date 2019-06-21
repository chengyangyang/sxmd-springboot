package com.sxmd.helper;

import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
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
        StringBuilder sqlColums = new StringBuilder();
        Set<String> columns = EntityHelper.getColumnNameList(aClass).getColumnNamesExcludePkIdName();
        Iterator<String> iterator = columns.iterator();
        while (iterator.hasNext()){
            sqlColums.append(iterator.next() + ",");
        }
        String sql = sqlColums.toString();
        if(StringUtils.isEmpty(getSqlPkIdName(aClass))){
            sql = sqlColums.substring(0,sql.length() - 1);
        }else {
            sql += getSqlPkIdName(aClass);
        }
        return sql;
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
        return EntityHelper.getPkIdName(aClass);
    }

    /**
     * Description:   获取 where条件sql
     * @author cy
     * @param obj: 条件对象
     * @return java.lang.String
     * @date  2019/6/20 18:21
     */
    public static String getSqlWhereCondition(Object obj){
        if(Objects.isNull(obj)){
            return "";
        }
        List<Field> fields = EntityHelper.getHasValueFieldsList(obj);
        if(Objects.isNull(fields) || fields.size() <= 0){
            return "";
        }
        StringBuilder sql = new StringBuilder();
        sql.append(" where ");
        // 添加第一个条件
        sql.append(StringHelper.underscoreName(fields.get(0).getName()));
        sql.append(" = #{");
        sql.append(fields.get(0).getName());
        sql.append("}");
        // 添加剩下的条件
        if(fields.size() > 1){
            fields.remove(0);
            fields.forEach(x->{
                sql.append(" and ");
                sql.append(StringHelper.underscoreName(x.getName()));
                sql.append(" = #{");
                sql.append(x.getName());
                sql.append("}");
            });
        }
        return sql.toString();
    }

}
