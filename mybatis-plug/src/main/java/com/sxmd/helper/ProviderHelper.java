package com.sxmd.helper;

import com.sxmd.exception.SxmdException;
import com.sxmd.mapper.BaseDeleteMapper;
import com.sxmd.mapper.BaseSelectMapper;
import org.apache.ibatis.builder.annotation.ProviderContext;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Objects;

/**
 * Description: provider 用到的工具类
 *
 * @author cy
 * @date 2019年06月21日 16:43
 * Version 1.0
 */
public class ProviderHelper {

    /**
     * Description:   判断是否为空 为空抛出异常
     * @author cy
     * @param obj:
     * @return void
     * @date  2019/6/21 16:12
     */
    public static void isNullThrowException(Object obj){
        if(Objects.isNull(obj)){
            throw new SxmdException("传入的对象不能为空！");
        }
    }

    /**
     * Description:   主键的校验
     * @author cy
     * @param id:
     * @return void
     * @date  2019/6/21 17:49
     */
    public static void isNullPkIdNameThrowException(String id) {
        if("".equals(id) || id == null){
            throw new SxmdException("获取不到主键的名称");
        }
    }

    /**
     * Description:   获得BaseSelectMapper 泛型的类型
     * @author cy
     * @param context:
     * @return java.lang.Class<?>
     * @date  2019/6/21 17:25
     */
    public static Class<?> getBaseSelectMapperParameterizedType(ProviderContext context) {
        return Arrays.stream(context.getMapperType().getGenericInterfaces())
                .filter(x -> x instanceof ParameterizedType)
                .map(ParameterizedType.class::cast)
                .filter(x -> x.getRawType() == BaseSelectMapper.class)
                .findFirst().map(x -> x.getActualTypeArguments()[0])
                .filter(Class.class::isInstance).map(Class.class::cast)
                .orElseThrow(()->new SxmdException("未找到BaseSelectMapper的泛型"));
    }

    /**
     * Description:   获得BaseDeleteMapper 泛型的类型
     * @author cy
     * @param context:
     * @return java.lang.Class<?>
     * @date  2019/6/21 17:25
     */
    public static Class<?> getBaseDeleteMapperParameterizedType(ProviderContext context) {
        return Arrays.stream(context.getMapperType().getGenericInterfaces())
                .filter(x -> x instanceof ParameterizedType)
                .map(ParameterizedType.class::cast)
                .filter(x -> x.getRawType() == BaseDeleteMapper.class)
                .findFirst().map(x -> x.getActualTypeArguments()[0])
                .filter(Class.class::isInstance).map(Class.class::cast)
                .orElseThrow(()->new SxmdException("未找到BaseDeleteMapper的泛型"));
    }


}
