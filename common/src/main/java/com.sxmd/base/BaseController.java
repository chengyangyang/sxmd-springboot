package com.sxmd.base;

import java.util.Objects;

/**
 * Description: 返回数据对象
 *
 * @author cy
 * @date 2019年08月06日 15:52
 * Version 1.0
 */
public class BaseController {


	public AjaxResult success(Object data) {
		if(null != data){
			return new AjaxResult().success(data);
		}else {
			return new AjaxResult().error(ResponseCodeEnum.CODE_9991);
		}
	}

	public AjaxResult fail(String message) {
		return new AjaxResult().error(message);
	}

	public AjaxResult fail(ResponseCodeEnum responseCodeEnum) {
		return new AjaxResult().error(responseCodeEnum);
	}

	public AjaxResult success(BasePage pageInfo) {
		if(Objects.isNull(pageInfo.getContent()) || pageInfo.getContent().isEmpty()){
			return new AjaxResult().error("当前无数据");
		}else {
			return new AjaxResult().success(pageInfo);
		}
	}

}
