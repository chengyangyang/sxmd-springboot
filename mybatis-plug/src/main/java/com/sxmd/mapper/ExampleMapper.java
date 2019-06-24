package com.sxmd.mapper;

/**
 * Description: crud的扩展类
 *
 * @author cy
 * @date 2019年06月24日 14:57
 * Version 1.0
 */
public class ExampleMapper {

    private Class<?> objClass;

    public ExampleMapper(Class objClass) {
        this.objClass = objClass;
    }

    public Class getObjClass() {
        return objClass;
    }
}
