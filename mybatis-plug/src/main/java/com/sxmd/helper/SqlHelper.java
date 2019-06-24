package com.sxmd.helper;

import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.text.MessageFormat;
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

    /**
     * Description:   插入语句的拼接（同时拼接字段，和值）
     * @author cy
     * @param aClass:
     * @param isHasId: 是否包含主键
     * @return java.lang.String
     * @date  2019/6/24 9:38
     */
    public static String getSqlInsertColumns(Class<?> aClass,boolean isHasId) {
        StringBuilder sqlColums = new StringBuilder();
        StringBuilder valueColums = new StringBuilder();
        Set<String> columns = EntityHelper.getColumnNameList(aClass).getColumnNamesExcludePkIdName();
        Iterator<String> iterator = columns.iterator();
        while (iterator.hasNext()){
            final String next = iterator.next();
            valueColums.append("#{");
            valueColums.append(StringHelper.camelCaseName(next));
            valueColums.append("},");
            sqlColums.append(next + ",");
        }
        String sql = sqlColums.toString();
        String valueSql = valueColums.toString();
        if(StringUtils.isEmpty(getSqlPkIdName(aClass)) || !isHasId){
            sql = sql.substring(0,sql.length() - 1);
            valueSql = valueSql.substring(0,sql.length() - 1);
        }else {
            sql += getSqlPkIdName(aClass);
            valueSql += "#{"+StringHelper.camelCaseName(getSqlPkIdName(aClass))+"}";
        }

        // 拼接生成新的语句
        sql = "(" + sql + ") " + "values" + " (" + valueSql + ")";
        return sql;
    }

    /**
     * Description:   批量插入语句的拼接（同时拼接字段，和值）
     * @author cy
     * @param list:  list 集合
     * @param aClass:
     * @param isHasId: 是否包含主键
     * @return java.lang.String
     * @date  2019/6/24 9:38
     */
    public static String getSqlInsertAllColumns(List<Object> list,Class<?> aClass, boolean isHasId) {
        StringBuilder sqlColums = new StringBuilder();
        StringBuilder valueColums = new StringBuilder();
        Set<String> columns = EntityHelper.getColumnNameList(aClass).getColumnNamesExcludePkIdName();
        Iterator<String> iterator = columns.iterator();
        while (iterator.hasNext()){
            // 拼接值
            final String next = iterator.next();
            valueColums.append("#'{'list[{0}].");
            valueColums.append(StringHelper.camelCaseName(next));
            valueColums.append("'}',");
            // 拼接字段
            sqlColums.append(next + ",");
        }
        String sql = sqlColums.toString();
        String valueSql = valueColums.toString();
        if(StringUtils.isEmpty(getSqlPkIdName(aClass)) || !isHasId){
            sql = sql.substring(0,sql.length() - 1);
            // 对list 的value进行拼接
            valueSql = valueSql.substring(0,sql.length() - 1);
        }else {
            sql += getSqlPkIdName(aClass);
            // 在字符串替换中，要使用单引号‘{’ 处理符号
            valueSql += "#'{'list[{0}]."+StringHelper.camelCaseName(getSqlPkIdName(aClass))+"'}'";
        }
        // 对新的value 进行拼接
        valueSql =" (" + valueSql + "),";
        StringBuilder newValueSql = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            newValueSql.append(MessageFormat.format(valueSql,i));
        }

        // 拼接生成新的语句
        sql = "(" + sql + ") " + "values" + newValueSql.substring(0,newValueSql.length() -1);
        return sql;
    }

    /**
     * Description:   单个更新语句的拼接
     * @author cy
     * @param aClass:
     * @return java.lang.String
     * @date  2019/6/24 13:51
     */
    public static String getSqlUpdateColumns(Class<?> aClass) {
        StringBuilder sqlColums = new StringBuilder();
        Set<String> columns = EntityHelper.getColumnNameList(aClass).getColumnNamesExcludePkIdName();
        Iterator<String> iterator = columns.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            sqlColums.append(next + " = #{");
            sqlColums.append(StringHelper.camelCaseName(next));
            sqlColums.append("},");
        }
        return sqlColums.substring(0,sqlColums.length() - 1);

    }

    /**
     * Description:   删除语句的拼接
     * @author cy
     * @param aClass:
     * @return java.lang.String
     * @date  2019/6/24 14:27
     */
    public static String getSqlWhereDeleteColumns(Class<?> aClass) {
        StringBuilder sqlColums = new StringBuilder();
        Set<String> columns = EntityHelper.getColumnNameList(aClass).getColumnNamesExcludePkIdName();
        Iterator<String> iterator = columns.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            sqlColums.append(next + " = #{");
            sqlColums.append(StringHelper.camelCaseName(next));
            sqlColums.append("} and ");
        }
        return sqlColums.substring(0,sqlColums.length() - 4);
    }

    /**
     * Description:   批量删除
     * @author cy
     * @param list:
     * @return java.lang.String
     * @date  2019/6/24 14:46
     */
    public static String getSqlDeleteIds(List list) {
        StringBuilder sql = new StringBuilder();
        list.forEach(x->{
            sql.append(x + ",");
        });
        return sql.substring(0,sql.length() - 1);
    }
}
