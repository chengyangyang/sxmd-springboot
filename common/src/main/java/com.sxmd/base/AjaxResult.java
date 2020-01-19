package com.sxmd.base;

import lombok.Data;

import java.io.Serializable;

/**
 * Description: 返回数据对象
 *
 * @author cy
 * @date 2019年08月06日 15:52
 * Version 1.0
 */
@Data
public class AjaxResult implements Serializable {

    private String message;
    private Object data;
    private String code;

    public AjaxResult() {
        ResponseCodeEnum success = ResponseCodeEnum.CODE_0000;
        this.code = success.getCode();
        this.message = success.getMessage();
    }

    public AjaxResult success() {
        ResponseCodeEnum success = ResponseCodeEnum.CODE_0000;
        this.code = success.getCode();
        this.message = success.getMessage();
        return this;
    }

    public AjaxResult success(ResponseCodeEnum responseEnum,Object data) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
        this.data = data;
        return this;
    }
    
    public AjaxResult success(Object data) {
        ResponseCodeEnum success = ResponseCodeEnum.CODE_0000;
        this.code = success.getCode();
        this.message = success.getMessage();
        this.data = data;
        return this;
    }

    public AjaxResult error(ResponseCodeEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
        return this;
    }

    public AjaxResult error(String message) {
        this.code = ResponseCodeEnum.CODE_9999.getCode();
        this.message = message;
        return this;
    }

    public AjaxResult error(ResponseCodeEnum responseEnum,String message) {
        this.code = responseEnum.getCode();
        this.message = message;
        return this;
    }

}
