package com.sxmd.provider;

import com.sxmd.helper.ProviderHelper;
import com.sxmd.helper.SqlHelper;

import java.util.List;
import java.util.Map;

/**
 * Description: 插入语句的组装
 *
 * @author cy
 * @date 2019年06月24日 9:25
 * Version 1.0
 */
public class BaseInsertProvider {


    /**
     * Description:   单个插入
     * @author cy
     * @param obj:
     * @return java.lang.String
     * @date  2019/6/24 13:38
     */
    public String insert(Object obj)  {
        // 校验
        ProviderHelper.isNullThrowException(obj);
        StringBuilder sql = new StringBuilder();
        sql.append("insert into ");
        sql.append(SqlHelper.getSqlTableName(obj.getClass()));
        sql.append(SqlHelper.getSqlInsertColumns(obj.getClass(),true));
        return sql.toString();
    }

    /**
     * Description:   批量插入
     * @author cy
     * @param obj:
     * @return java.lang.String
     * @date  2019/6/24 13:39
     */
    public String insertAll(Object obj)  {
        // 校验
        Map map = (Map) obj;
        List list = (List)map.get("list");
        Class<?> aClass = list.get(0).getClass();
        StringBuilder sql = new StringBuilder();
        sql.append("insert into ");
        sql.append(SqlHelper.getSqlTableName(aClass));
        sql.append(SqlHelper.getSqlInsertAllColumns(list,aClass,true));
        return sql.toString();
    }


}
