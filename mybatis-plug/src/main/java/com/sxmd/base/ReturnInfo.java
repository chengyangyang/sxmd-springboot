package com.sxmd.base;

/**
 * Description: 返回信息
 *
 * @author cy
 * @date 2019年08月07日 15:55
 * Version 1.0
 */
public interface ReturnInfo {

    String KEY_INDEX = "/index";
    String KEY_MAIN = "/";
    String KEY_ADD = "/add";
    String KEY_EDIT = "/edit";
    String KEY_VIEW = "/view";
    String KEY_LIST = "/list";
    String KEY_SAVE = "/save";
    String KEY_UPDATE = "/update";
    String KEY_REMOVE = "/remove";
    String KEY_DEL = "/del";
    String KEY_RESTORE = "/restore";
    String SAVE_SUCCESS_MSG = "新增成功！";
    String SAVE_FAIL_MSG = "新增失败！";
    String QUERY_SUCCESS_MSG = "查询成功！";
    String QUERY_FAIL_MSG = "查询失败！";
    String UPDATE_SUCCESS_MSG = "修改成功！";
    String UPDATE_FAIL_MSG = "修改失败！";
    String DEL_SUCCESS_MSG = "删除成功！";
    String DEL_FAIL_MSG = "删除失败！";
}
