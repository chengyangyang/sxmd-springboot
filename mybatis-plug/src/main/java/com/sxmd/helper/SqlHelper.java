package com.sxmd.helper;


import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description:  拼接sql
 *
 * @author cy
 * @date 2019年06月20日 17:15
 * Version 1.0
 */
public class SqlHelper {

    // 存储getSqlColumns 的信息（通用）
    private static final Map<Class<?>, String> sqlColumnsMap = new ConcurrentHashMap();

    // 存储 getSqlInsertColumns 的信息（通用 带主键）
    private static final Map<Class<?>, String> sqlInsertColumnsMap = new ConcurrentHashMap();

    // 存储 getSqlInsertColumns 的信息（通用 不带主键）
    private static final Map<Class<?>, String> sqlInsertColumnsNotIdMap = new ConcurrentHashMap();

    /**
     * Description:   获取所有的列(用逗号隔开 含有主键)
     * @author cy
     * @param aClass:
     * @return java.lang.String
     * @date  2019/6/20 18:21
     */
    public static String getSqlColumns(final Class<?> aClass){
        return sqlColumnsMap.get(aClass);
    }

    /**
     * Description:   设置查询字段拼接(用逗号隔开 含有主键)
     * @author cy
     * @param aClass:
     * @return java.lang.String
     * @date  2019/6/20 18:21
     */
    public static void setSqlColumns(final Class<?> aClass){
        StringBuilder sqlColums = new StringBuilder();
        LinkedHashMap<String, String> column = EntityHelper.getColumnExcludePkIdList(aClass);
        for (Map.Entry<String, String> co: column.entrySet()) {
            sqlColums.append(co.getValue() + ",");
        }
        sqlColums.append(getPkIdSqlName(aClass));
        sqlColumnsMap.put(aClass,sqlColums.toString());
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
     * Description:   获取主键sql名称
     * @author cy
     * @param aClass:
     * @return java.lang.String
     * @date  2019/6/20 18:21
     */
    public static String getPkIdSqlName(Class<?> aClass){
        return EntityHelper.getPkIdSqlName(aClass);
    }

    /**
     * Description:   获取主键字段名称
     * @author cy
     * @param aClass:
     * @return java.lang.String
     * @date  2019/6/20 18:21
     */
    public static String getPkIdName(Class<?> aClass){
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
        // 得到对应的列
        LinkedHashMap<String, String> columnExcludePkId = EntityHelper.getColumnExcludePkIdList(obj.getClass());

        StringBuilder sql = new StringBuilder();
        sql.append(" where ");
        // 添加第一个条件
        sql.append(columnExcludePkId.get(fields.get(0).getName()));
        sql.append(" = #{");
        sql.append(fields.get(0).getName());
        sql.append("}");
        // 添加剩下的条件
        if(fields.size() > 1){
            fields.remove(0);
            fields.forEach(x->{
                sql.append(" and ");
                sql.append(columnExcludePkId.get(x.getName()));
                sql.append(" = #{");
                sql.append(x.getName());
                sql.append("}");
            });
        }
        return sql.toString();
    }

    /**
     * Description:   单条插入语句的拼接（同时拼接字段，和值）
     * @author cy
     * @param aClass:
     * @param isHasId: 是否包含主键
     * @return java.lang.String
     * @date  2019/6/24 9:38
     */
    public static String getSqlInsertColumns(Class<?> aClass,boolean isHasId) {
        if(isHasId){
            return sqlInsertColumnsMap.get(aClass);
        }else {
            return sqlInsertColumnsNotIdMap.get(aClass);
        }
    }

    /**
     * Description:   设置单条插入语句的拼接（同时拼接字段，和值）
     * @author cy
     * @param aClass:
     * @return java.lang.String
     * @date  2019/6/24 9:38
     */
    public static void setSqlInsertColumns(Class<?> aClass) {
        StringBuilder sqlColums = new StringBuilder();
        StringBuilder valueColums = new StringBuilder();
        LinkedHashMap<String, String> column = EntityHelper.getColumnExcludePkIdList(aClass);
        for (Map.Entry<String, String> co: column.entrySet()) {
            valueColums.append("#{");
            valueColums.append(co.getKey());
            valueColums.append("},");
            sqlColums.append(co.getValue() + ",");
        }
        sqlColums.append(EntityHelper.getPkIdSqlName(aClass));
        valueColums.append("#{"+EntityHelper.getPkIdName(aClass)+"}");
        // 拼接生成新的语句
        String sql = "(" + sqlColums.toString() + ") " + "values" + " (" + valueColums.toString() + ")";
        sqlInsertColumnsMap.put(aClass,sql);
    }

    /**
     * Description:   设置单条插入语句的拼接（同时拼接字段，和值）
     * @author cy
     * @param aClass:
     * @return java.lang.String
     * @date  2019/6/24 9:38
     */
    public static void setSqlInsertNotIdColumns(Class<?> aClass) {
        StringBuilder sqlColums = new StringBuilder();
        StringBuilder valueColums = new StringBuilder();
        LinkedHashMap<String, String> column = EntityHelper.getColumnExcludePkIdList(aClass);
        for (Map.Entry<String, String> co: column.entrySet()) {
            valueColums.append("#{");
            valueColums.append(co.getKey());
            valueColums.append("},");
            sqlColums.append(co.getValue() + ",");
        }
        // 拼接生成新的语句
        String sql = "(" + sqlColums.toString().substring(0,sqlColums.length() - 1) + ") " + "values" + " (" + valueColums.toString().substring(0,valueColums.length() - 1) + ")";
        sqlInsertColumnsNotIdMap.put(aClass,sql);
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
        LinkedHashMap<String, String> column = EntityHelper.getColumnExcludePkIdList(aClass);
        for (Map.Entry<String, String> co: column.entrySet()) {
            valueColums.append("#'{'list[{0}].");
            valueColums.append(co.getKey());
            valueColums.append("'}',");
            // 拼接字段
            sqlColums.append(co.getValue() + ",");
        }

        String sql = sqlColums.toString();
        String valueSql = valueColums.toString();
        if(!isHasId){
            sql = sql.substring(0,sql.length() - 1);
            // 对list 的value进行拼接
            valueSql = valueSql.substring(0,sql.length() - 1);
        }else {
            sql += EntityHelper.getPkIdSqlName(aClass);
            // 在字符串替换中，要使用单引号‘{’ 处理符号
            valueSql += "#'{'list[{0}]."+EntityHelper.getPkIdName(aClass)+"'}'";
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
        LinkedHashMap<String, String> column = EntityHelper.getColumnExcludePkIdList(aClass);
        for (Map.Entry<String, String> co: column.entrySet()) {
            sqlColums.append(co.getKey() + " = #{");
            sqlColums.append(co.getValue());
            sqlColums.append("},");
        }
        return sqlColums.substring(0,sqlColums.length() - 1);
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
