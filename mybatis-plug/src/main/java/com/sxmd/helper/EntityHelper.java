package com.sxmd.helper;

import com.sxmd.bean.EntityTable;
import com.sxmd.exception.SxmdException;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Description: 实体类工具类
 *
 * @author cy
 * @date 2019年06月19日 17:54
 * Version 1.0
 */
public class EntityHelper {


    // 存储所有的entity信息
    private static final Map<Class<?>, EntityTable> entityTableMap = new ConcurrentHashMap();

    /**
     * Description:   获取实体表名称
     * @author cy
     * @param entityClass: class
     * @return com.sxmd.bean.EntityTable
     * @date  2019/6/19 18:20
     */
    public static String getEntityTableName(Class<?> entityClass) {
        String tableSqlName = getColumnNameList(entityClass).getTableSqlName();
        if (StringHelper.isBlank(tableSqlName)) {
            throw new SxmdException("无法获取实体类" + entityClass.getCanonicalName() + "对应的表名!");
        } else {
            return tableSqlName;
        }
    }

    /**
     * Description:   获取主键名称
     * @author cy
     * @param entityClass: class
     * @return com.sxmd.bean.EntityTable
     * @date  2019/6/19 18:20
     */
    public static String getPkIdName(Class<?> entityClass) {
        String pkIdName = getColumnNameList(entityClass).getPkIdName();
        if (StringHelper.isBlank(pkIdName)) {
            throw new SxmdException("无法获取实体类" + entityClass.getCanonicalName() + "对应的主键名称!");
        } else {
            return pkIdName;
        }
    }

    /**
     * Description:   获取主键sql 名称
     * @author cy
     * @param entityClass: class
     * @return com.sxmd.bean.EntityTable
     * @date  2019/6/19 18:20
     */
    public static String getPkIdSqlName(Class<?> entityClass) {
        String pkIdSqlName = getColumnNameList(entityClass).getPkIdSqlName();
        if (StringHelper.isBlank(pkIdSqlName)) {
            throw new SxmdException("无法获取实体类" + entityClass.getCanonicalName() + "对应的主键sql名称!");
        } else {
            return pkIdSqlName;
        }
    }

    /**
     * Description:   获得实体
     * @author cy
     * @param entityClass:
     * @return com.sxmd.bean.EntityTable
     * @date  2019/6/21 18:08
     */
    public static EntityTable getColumnNameList(Class<?> entityClass) {
        EntityTable entityTable = entityTableMap.get(entityClass);
        if (entityTable == null) {
            throw new SxmdException("无法获取实体类" + entityClass.getCanonicalName() + "对应的数据库类型!");
        } else {
            return entityTable;
        }
    }

    /**
     * Description:   获得除主键的列
     * @author cy
     * @param entityClass:
     * @return java.util.LinkedHashMap<java.lang.String,java.lang.String>
     * @date  2019/6/25 10:58
     */
    public static LinkedHashMap<String, String> getColumnExcludePkIdList(Class<?> entityClass) {
         return getColumnNameList(entityClass).getColumnExcludePkId();
    }

    /**
     * Description:   初始化表信息
     * @author cy
     * @param map:
     * @return void
     * @date  2019/6/20 13:46
     */
    public static void intTableInfo(Map<String, Object> map){
        for(Map.Entry<String, Object> obj: map.entrySet()){
            Class<?> aClass = obj.getValue().getClass();
            // 这个版本好像有问题不能用下面的进行判断
            // Class<?> aClass = AopUtils.getTargetClass(obj.getValue());
            if(ClassUtils.isCglibProxyClass(aClass)){
                aClass = aClass.getSuperclass();
            }
            entityTableMap.put(aClass,
                    new EntityTable()
                            .setTableSqlName(setTableSqlName(aClass))
                            .setColumnExcludePkId(setColumnExcludePkId(aClass))
                            .setPkIdName(setPkIdName(aClass))
                            .setPkIdSqlName(StringHelper.underscoreName(setPkIdName(aClass)))
                    );
            // 存储sql 的拼接
            SqlHelper.setSqlColumns(aClass);
            SqlHelper.setSqlInsertColumns(aClass);
            SqlHelper.setSqlInsertNotIdColumns(aClass);
        }
    }


    /**
     * Description:   设置 sql主键名称 默认为id
     * @author cy
     * @param aClass:
     * @return java.lang.String
     * @date  2019/6/20 18:12
     */
    public static String setPkIdName(Class<?> aClass){
        List<Field> fields = getAllFieldsList(aClass);
        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            if(field.isAnnotationPresent(Id.class)){
                return field.getName();
            }
        }
        return "id";
    }

    /**
     * Description:  设置除主键之外的sql 字段名称
     * @author cy
     * @param aClass:
     * @return
     * @date  2019/6/20 18:14
     */
    public static LinkedHashMap<String,String> setColumnExcludePkId(Class<?> aClass){
        LinkedHashMap<String,String> columnsName = new LinkedHashMap<>();
        List<Field> fields = getAllFieldsList(aClass);
        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            if(!field.isAnnotationPresent(Id.class)){
                columnsName.put(field.getName(),StringHelper.underscoreName(field.getName()));
            }
        }
        return columnsName;
    }

    /**
     * Description:   设置表名称
     * @author cy
     * @param aClass:
     * @return java.lang.String
     * @date  2019/6/21 9:57
     */
    public static String setTableSqlName(Class<?> aClass){
        String tableName = aClass.getAnnotation(Table.class).name();
        if(StringUtils.isEmpty(tableName)){
            tableName = StringHelper.underscoreName(aClass.getSimpleName());
        }
        return tableName;
    }

    /**
     * Description:   获得类中所有属性
     * @author cy
     * @param cls:
     * @return java.util.List<java.lang.reflect.Field>
     * @date  2019/6/21 9:58
     */
    public static List<Field> getAllFieldsList(final Class<?> cls) {
        final List<Field> allFields = new ArrayList<Field>();
        Class<?> currentClass = cls;
        while (currentClass != null) {
            final Field[] declaredFields = currentClass.getDeclaredFields();
            for (final Field field : declaredFields) {
                allFields.add(field);
            }
            currentClass = currentClass.getSuperclass();
        }
        return allFields;
    }

    /**
     * Description:   查询对象中有值的字段 并对主键进行过滤集合
     * @author cy
     * @param obj:
     * @return java.util.List<java.lang.reflect.Field>
     * @date  2019/6/21 10:42
     */
    public static List<Field> getHasValueFieldsList(Object obj)  {
        return getAllFieldsList(obj.getClass()).stream().filter(x -> {
            x.setAccessible(true);
            try {
                return !Objects.isNull(x.get(obj)) || !x.isAnnotationPresent(Id.class);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
        }).collect(Collectors.toList());
    }


}



