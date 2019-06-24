package com.sxmd.provider;

import com.sxmd.exception.SxmdException;
import com.sxmd.helper.ProviderHelper;
import com.sxmd.helper.SqlHelper;
import com.sxmd.mapper.BaseSelectMapper;
import org.apache.ibatis.builder.annotation.ProviderContext;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.MappedStatement;
import org.springframework.jdbc.core.SqlProvider;
import org.springframework.util.StringUtils;

import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;


/**
 * Description: 基本查询
 *
 * @author cy
 * @date 2019年06月19日 11:01
 * Version 1.0
 */
public class BaseSelectProvider {

    /**
     * Description:   单条sql 查询为单个
     * @author cy
     * @param obj:  泛型对象
     * @return java.lang.String
     * @date  2019/6/21 11:35
     */
    public String selectOne(Object obj)  {
        // 校验
        ProviderHelper.isNullThrowException(obj);
        StringBuilder sql = new StringBuilder();
        sql.append("select ");
        sql.append(SqlHelper.getSqlColumns(obj.getClass()));
        sql.append(" from ");
        sql.append(SqlHelper.getSqlTableName(obj.getClass()));
        sql.append(SqlHelper.getSqlWhereCondition(obj));
        return sql.toString();
    }

    /**
     * Description:   条件查询 得到列表数据
     * @author cy
     * @param obj:  泛型对象
     * @return java.lang.String
     * @date  2019/6/21 11:35
     */
    public String select(Object obj)  {
        // 校验
        ProviderHelper.isNullThrowException(obj);
        StringBuilder sql = new StringBuilder();
        sql.append("select ");
        sql.append(SqlHelper.getSqlColumns(obj.getClass()));
        sql.append(" from ");
        sql.append(SqlHelper.getSqlTableName(obj.getClass()));
        sql.append(SqlHelper.getSqlWhereCondition(obj));
        return sql.toString();
    }

    /**
     * Description:  条件查询数量
     * @author cy
     * @param obj:
     * @return java.lang.String
     * @date  2019/6/21 17:34
     */
    public String selectCount(Object obj)  {
        // 校验
        ProviderHelper.isNullThrowException(obj);
        StringBuilder sql = new StringBuilder();
        sql.append("select ");
        sql.append(" count(1) ");
        sql.append(" from ");
        sql.append(SqlHelper.getSqlTableName(obj.getClass()));
        sql.append(SqlHelper.getSqlWhereCondition(obj));
        return sql.toString();
    }

    /**
     * Description: 条件查询是否存在
     * @author cy
     * @param obj:
     * @return java.lang.String
     * @date  2019/6/21 18:20
     */
    public String exists(Object obj)  {
        // 校验
        ProviderHelper.isNullThrowException(obj);
        StringBuilder sql = new StringBuilder();

        sql.append("select IFNULL((");
        sql.append("select ");
        sql.append(" 1 ");
        sql.append(" from ");
        sql.append(SqlHelper.getSqlTableName(obj.getClass()));
        sql.append(SqlHelper.getSqlWhereCondition(obj));
        sql.append(" limit 1");
        sql.append("),0)");
        return sql.toString();
    }


    /**
     * Description:   是否存在该主键
     * @author cy
     * @param context:
     * @return java.lang.String
     * @date  2019/6/24 8:57
     */
    public String existsWithPrimaryKey(ProviderContext context)  {
        Class<?> aclass = ProviderHelper.getBaseSelectMapperParameterizedType(context);
        ProviderHelper.isNullThrowException(aclass);
        // 查询主键字段
        String pkIdName = SqlHelper.getSqlPkIdName(aclass);
        ProviderHelper.isNullPkIdNameThrowException(pkIdName);

        StringBuilder sql = new StringBuilder();
        sql.append("select IFNULL((");
        sql.append("select ");
        sql.append(" 1 ");
        sql.append(" from ");
        sql.append(SqlHelper.getSqlTableName(aclass));
        sql.append(" where ");
        sql.append(pkIdName);
        sql.append(" = #{id}");
        sql.append(" limit 1");
        sql.append("),0)");
        return sql.toString();
    }


    /**
     * Description:   根据主键进行查询
     * @author cy
     * @param context:
     * @return java.lang.String
     * @date  2019/6/21 17:42
     */
    public String selectByPrimaryKey(ProviderContext context)  {
        Class<?> aclass = ProviderHelper.getBaseSelectMapperParameterizedType(context);
        ProviderHelper.isNullThrowException(aclass);
        // 查询主键字段
        String pkIdName = SqlHelper.getSqlPkIdName(aclass);
        ProviderHelper.isNullPkIdNameThrowException(pkIdName);

        StringBuilder sql = new StringBuilder();
        sql.append("select ");
        sql.append(SqlHelper.getSqlColumns(aclass));
        sql.append(" from ");
        sql.append(SqlHelper.getSqlTableName(aclass));
        sql.append(" where ");
        sql.append(pkIdName);
        sql.append(" = #{id}");
        return sql.toString();
    }


    /**
     * Description:   条件查询 得到列表数据
     * @author cy
     * @param context:  泛型对象
     * @return java.lang.String
     * @date  2019/6/21 11:35
     */
    public String selectAll(ProviderContext context)  {
        Class<?> aclass = ProviderHelper.getBaseSelectMapperParameterizedType(context);
        ProviderHelper.isNullThrowException(aclass);
        StringBuilder sql = new StringBuilder();
        sql.append("select ");
        sql.append(SqlHelper.getSqlColumns(aclass));
        sql.append(" from ");
        sql.append(SqlHelper.getSqlTableName(aclass));
        return sql.toString();
    }

}
