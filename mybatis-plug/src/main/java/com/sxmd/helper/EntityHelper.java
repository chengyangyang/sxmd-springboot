package com.sxmd.helper;

import com.sxmd.bean.EntityTable;
import com.sxmd.exception.SxmdException;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    // 获取实体的表名称
    public static EntityTable getColumnNameList(Class<?> entityClass) {
        EntityTable entityTable = (EntityTable)entityTableMap.get(entityClass);
        entityTable.getColumnName();
        if (entityTable == null) {
            throw new SxmdException("无法获取实体类" + entityClass.getCanonicalName() + "对应的表名!");
        } else {
            return entityTable;
        }
    }



}
