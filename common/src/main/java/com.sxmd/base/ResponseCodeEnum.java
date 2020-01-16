package com.sxmd.base;


/**
 * Description: 对象返回码
 *
 * @author cy
 * @date 2019年09月23日 14:30
 * Version 1.0
 */
public enum ResponseCodeEnum {

    CODE_0000("CODE_0000", "请求成功"),
    CODE_9991("CODE_9991", "对象不存在"),
    CODE_9992("CODE_9992", "对象已存在"),
    CODE_9993("CODE_9993", "非法的请求参数"),
    CODE_9994("CODE_9994", "非法的文件类型"),
    CODE_9995("CODE_9995", "文件不能为空"),
    CODE_9999("CODE_9999", "请求失败"),
    ;

    private String code;
    private String message;

    ResponseCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
