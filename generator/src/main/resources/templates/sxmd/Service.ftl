package com.sxmd.content.${folderName!''}.service;

import com.sxmd.base.BasePage;
import com.sxmd.content.${folderName!''}.model.am.${table.tableNameToJavaName}AddModel;
import com.sxmd.content.${folderName!''}.model.dm.${table.tableNameToJavaName}Model;
import com.sxmd.content.${folderName!''}.model.em.${table.tableNameToJavaName}EditModel;
import com.sxmd.content.${folderName!''}.model.lm.${table.tableNameToJavaName}ListModel;

import java.util.Map;

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
     * @return boolean
     * @date
     */
    boolean insert${table.tableNameToJavaName}(${table.tableNameToJavaName}AddModel model);

    /**
     * Description:  ${table.tableComment!''}-更新
     * @author sxmd
     * @param model:
     * @date
     */
    boolean update${table.tableNameToJavaName}(${table.tableNameToJavaName}EditModel model);

    /**
     * Description:   ${table.tableComment!''}-删除
     * @author sxmd
     * @param id: 主键
     * @date
     */
    Boolean delete${table.tableNameToJavaName}(Long id);

    /**
     * Description:   ${table.tableComment!''}-详情
     * @author sxmd
     * @param id: 主键
     * @date
     */
    ${table.tableNameToJavaName}Model get${table.tableNameToJavaName}ById(Long id);

    /**
     * Description:   ${table.tableComment!''}-列表
     * @author sxmd
     * @param pageNum: 第几页
     * @param pageSize: 显示条数
     * @param map:  条件
     * @date
     */
    BasePage<${table.tableNameToJavaName}ListModel> find${table.tableNameToJavaName}List(Integer pageNum, Integer pageSize, Map<String,Object> map);


}
