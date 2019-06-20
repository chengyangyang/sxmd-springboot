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
        EntityTable entityTableName = entityTableMap.get(entityClass);
        if (entityTableName == null) {
            throw new SxmdException("无法获取实体类" + entityClass.getCanonicalName() + "对应的表名!");
        } else {
            return entityTableName.getTableName();
        }
    }

    // 获取实体的列名称
    public static EntityTable getColumnNameList(Class<?> entityClass) {
        EntityTable entityTable = entityTableMap.get(entityClass);
       // entityTable.getColumnName();
        if (entityTable == null) {
            throw new SxmdException("无法获取实体类" + entityClass.getCanonicalName() + "对应的表名!");
        } else {
            return entityTable;
        }
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
                            .setTableName(setTableName(aClass))
                            .setColumnNamesExcludePkIdName(setColumnsName(aClass))
                            .setPkIdName(setPkIdName(aClass))
                    );
        }
    }


    /**
     * Description:   获得 sql主键名称
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
                return StringHelper.underscoreName(field.getName());
            }
        }
        return null;
    }

    /**
     * Description:  获得除主键之外的sql 字段名称
     * @author cy
     * @param aClass:
     * @return java.util.LinkedHashSet<java.lang.String>
     * @date  2019/6/20 18:14
     */
    public static LinkedHashSet<String> setColumnsName(Class<?> aClass){
        LinkedHashSet<String> columnsNameSet = new LinkedHashSet<>();
        List<Field> fields = getAllFieldsList(aClass);
        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            if(!field.isAnnotationPresent(Id.class)){
                columnsNameSet.add(StringHelper.underscoreName(field.getName()));
            }
        }
        return columnsNameSet;
    }

    public static String setTableName(Class<?> aClass){
        String tableName = aClass.getAnnotation(Table.class).name();
        if(StringUtils.isEmpty(tableName)){
            tableName = StringHelper.underscoreName(aClass.getSimpleName());
        }
        return tableName;
    }

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

}



