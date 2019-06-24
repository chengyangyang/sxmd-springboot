package com.sxmd.provider;

import com.sxmd.helper.ProviderHelper;
import com.sxmd.helper.SqlHelper;
import org.apache.ibatis.builder.annotation.ProviderContext;

import java.util.List;
import java.util.Map;

/**
 * Description:  删除接口的拼接
 *
 * @author cy
 * @date 2019年06月24日 14:08
 * Version 1.0
 */
public class BaseDeleteProvider {

    /**
     * Description:   通过主键进行删除
     * @author cy
     * @param context:
     * @return java.lang.String
     * @date  2019/6/24 13:38
     */
    public String deleteByPrimaryKey(ProviderContext context)  {
        Class<?> aclass = ProviderHelper.getBaseDeleteMapperParameterizedType(context);
        ProviderHelper.isNullThrowException(aclass);
        // 查询主键字段
        String pkIdName = SqlHelper.getSqlPkIdName(aclass);
        ProviderHelper.isNullPkIdNameThrowException(pkIdName);

        StringBuilder sql = new StringBuilder();
        sql.append("delete ");
        sql.append(" from ");
        sql.append(SqlHelper.getSqlTableName(aclass));
        sql.append(" where ");
        sql.append(pkIdName);
        sql.append(" = #{id}");
        sql.append(" limit 1");
        return sql.toString();
    }
    /**
     * Description:   通过主键批量删除
     * @author cy
     * @param obj:
     * @param context:
     * @return java.lang.String
     * @date  2019/6/24 14:43
     */
    public String deleteByPrimaryKeys(Object obj,ProviderContext context)  {
        Map map = (Map) obj;
        List list = (List)map.get("ids");
        Class<?> aclass = ProviderHelper.getBaseDeleteMapperParameterizedType(context);
        ProviderHelper.isNullThrowException(aclass);
        // 查询主键字段
        String pkIdName = SqlHelper.getSqlPkIdName(aclass);
        ProviderHelper.isNullPkIdNameThrowException(pkIdName);

        StringBuilder sql = new StringBuilder();
        sql.append("delete ");
        sql.append(" from ");
        sql.append(SqlHelper.getSqlTableName(aclass));
        sql.append(" where ");
        sql.append(pkIdName);
        sql.append(" in (");
        sql.append(SqlHelper.getSqlDeleteIds(list));
        sql.append(")");
        return sql.toString();
    }


}
