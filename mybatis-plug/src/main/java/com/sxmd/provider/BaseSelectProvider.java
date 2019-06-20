package com.sxmd.provider;

import com.sxmd.helper.SqlHelper;
import org.apache.ibatis.builder.annotation.ProviderContext;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.MappedStatement;
import org.springframework.jdbc.core.SqlProvider;

import java.lang.reflect.Parameter;


/**
 * Description: 基本查询
 *
 * @author cy
 * @date 2019年06月19日 11:01
 * Version 1.0
 */
public class BaseSelectProvider {

    /**
     * Description:   单条查询sql
     * @author cy
     * @param context:
     * @return java.lang.String
     * @date  2019/6/19 11:04
     */
    public String selectOne(Object a,ProviderContext context)  {
        new SQL().INTO_COLUMNS();
        StringBuilder sql = new StringBuilder();
        sql.append("select ");
        sql.append(SqlHelper.getSqlColumns(a.getClass()));
        sql.append(" from ");
        sql.append(SqlHelper.getSqlTableName(a.getClass()));
        return sql.toString();
    }

}
