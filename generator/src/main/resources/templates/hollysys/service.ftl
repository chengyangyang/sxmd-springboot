package com.hollysys.smartfactory.account.system.service;

import com.github.pagehelper.PageInfo;
import com.hollysys.smartfactory.account.system.model.vm.${table.tableNameToJavaName}ListModel;
import com.hollysys.smartfactory.account.system.model.vm.${table.tableNameToJavaName}Model;
import com.hollysys.smartfactory.account.system.model.vr.${table.tableNameToJavaName}Model;
import com.hollysys.smartfactory.account.system.model.vr.${table.tableNameToJavaName}Model;

/**
* Description: ${table.tableComment!''}接口
*
* @author sxmd
* @date
* Version 1.0
*/
public interface ${fileName} {

    /**
    * Description:  ${table.tableComment!''}-新增
    * @author sxmd
    * @param model:
    * @return java.lang.String
    * @date  2019/6/12 10:30
    */
    String add${table.tableNameToJavaName}(${table.tableNameToJavaName}AddModel model);

    /**
    * Description:  ${table.tableComment!''}-更新
    * @author sxmd
    * @param model:
    * @date
    */
    String update${table.tableNameToJavaName}(${table.tableNameToJavaName}EditModel model);

    /**
    * Description:   ${table.tableComment!''}-删除
    * @author sxmd
    * @param id: 主键
    * @date
    */
    String delete${table.tableNameToJavaName}(String id);

    /**
    * Description:   ${table.tableComment!''}-详情
    * @author sxmd
    * @param id: 主键
    * @date
    */
    ${table.tableNameToJavaName}Model get${table.tableNameToJavaName}ById(String id);

    /**
    * Description:   ${table.tableComment!''}-列表
    * @author sxmd
    * @param page: 第几页
    * @param pageSize: 显示条数
    * @param map:  条件
    * @date
    */
    PageInfo<${table.tableNameToJavaName}ListModel> find${table.tableNameToJavaName}List(Integer page, Integer pageSize, Map<String,Object> map);


}