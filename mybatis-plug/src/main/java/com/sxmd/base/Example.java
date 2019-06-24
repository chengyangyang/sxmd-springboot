package com.sxmd.base;

import com.sxmd.helper.StringHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: crud的扩展类 条件类
 *
 * @author cy
 * @date 2019年06月24日 14:57
 * Version 1.0
 */
public class Example {


    private Map map = new HashMap<String,Object>();

    private StringBuilder sql = new StringBuilder();


    public Example andEqualTo(String property, Object value){
        String size = map.size() + "";
        sql.append(" and ");
        sql.append(StringHelper.underscoreName(property));
        sql.append("= #{");
        sql.append("map.");
        sql.append(size);
        sql.append("}");
        map.put(size,value);
        return this;
    }


    public String getSql() {
        return sql.toString();
    }
}
