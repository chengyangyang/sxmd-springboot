package com.sxmd.provider;

import com.sxmd.helper.ProviderHelper;
import com.sxmd.helper.SqlHelper;

/**
 * Description:  更新sql的组装
 *
 *
 * @author cy
 * @date 2019年06月24日 13:36
 * Version 1.0
 */
public class BaseUpdateProvider {

    /**
     * Description:   单个更新
     * @author cy
     * @param obj:
     * @return java.lang.String
     * @date  2019/6/24 13:39
     */
    public String updateByPrimaryKey(Object obj)  {
        ProviderHelper.isNullThrowException(obj.getClass());
        // 校验
        ProviderHelper.isNullThrowException(obj);
        StringBuilder sql = new StringBuilder();
        sql.append("update ");
        sql.append(SqlHelper.getSqlTableName(obj.getClass()));
        sql.append(" set ");
        sql.append(SqlHelper.getSqlUpdateColumns(obj.getClass()));
        sql.append(" where ");
        sql.append(SqlHelper.getPkIdSqlName(obj.getClass()));
        sql.append(" = #{");
        sql.append(SqlHelper.getPkIdName(obj.getClass()));
        sql.append("}");
        return sql.toString();
    }

}
