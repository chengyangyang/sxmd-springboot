package com.sxmd.content.${folderName}.dao;

import com.sxmd.base.BaseDao;
import com.sxmd.content.${folderName}.entity.${table.tableNameToJavaName};
import com.sxmd.content.${folderName}.model.lm.${table.tableNameToJavaName}ListModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* Description: ${table.tableComment!''} dao接口
*
* @author sxmd
* @date
* Version 1.0
*/
@Mapper
public interface ${fileName} extends BaseDao<${table.tableNameToJavaName}> {

    /**
    * Description: 列表查询
    * @author sxmd
    * @param map:
    * @return java.util.List
    * @date
    */
    List<${table.tableNameToJavaName}ListModel> find${table.tableNameToJavaName}List(@Param("map") Map<String,Object> map);
}