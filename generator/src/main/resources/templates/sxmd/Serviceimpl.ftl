package com.hollysys.smartfactory.account.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hollysys.smart.factory.exception.CustomException;
import com.hollysys.smartfactory.account.system.dao.mapper.${table.tableNameToJavaName}Mapper;
import com.hollysys.smartfactory.account.system.model.dm.Attachfile;
import com.hollysys.smartfactory.account.system.model.dm.${table.tableNameToJavaName};
import com.hollysys.smartfactory.account.system.model.vm.${table.tableNameToJavaName}ListModel;
import com.hollysys.smartfactory.account.system.model.vm.${table.tableNameToJavaName}Model;
import com.hollysys.smartfactory.account.system.model.vr.${table.tableNameToJavaName}AddModel;
import com.hollysys.smartfactory.account.system.model.vr.${table.tableNameToJavaName}EditModel;
import com.hollysys.smartfactory.account.system.service.${table.tableNameToJavaName}Service;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* Description: ${table.tableComment!''} 实现类
*
* @author sxmd
* @date
* Version 1.0
*/
@Service
public class ${fileName} extends BaseServiceImpl<${table.tableNameToJavaName}> implements ${table.tableNameToJavaName}Service {

    @Resource
    ${table.tableNameToJavaName}Mapper mapper;

    /**
    * Description:   新增
    * @author sxmd
    * @param model:
    * @date
    */
    @Override
    public String add${table.tableNameToJavaName}(${table.tableNameToJavaName}AddModel model) {
        ${table.tableNameToJavaName} entity = new ${table.tableNameToJavaName}();
        BeanUtils.copyProperties(model, entity);
        String result = this.insert(entity);
        return result;
    }

    /**
    * Description: 更新
    * @author sxmd
    * @param model:
    * @date
    */
    @Override
    public String update${table.tableNameToJavaName}(${table.tableNameToJavaName}EditModel model) {
        // 查询当前数据
        ${table.tableNameToJavaName} entity = this.selectByKeyIfNullThrowError(model.getPkid());
        // 校验
        checkCs(entity.getCs(), model.getCs());
        BeanUtils.copyProperties(model, entity);
        return this.updateAll(entity);
    }

    /**
    * Description: 删除
    * @author sxmd
    * @param id: 主键
    * @date
    */
    @Override
    public String delete${table.tableNameToJavaName}(String id) {
        this.selectByKeyIfNullThrowError(id);
        return this.delete(id);
    }


    /**
    * Description:   详情
    * @author sxmd
    * @param id: 主键
    * @date
    */
    @Override
    public ${table.tableNameToJavaName}Model getE${table.tableNameToJavaName}ById(String id) {
        ${table.tableNameToJavaName} entity = this.selectByKeyIfNullThrowError(id);
        ${table.tableNameToJavaName}Model model = new ${table.tableNameToJavaName}Model();
        BeanUtils.copyProperties(entity,model);
        return model;
    }


    /**
    * Description: 列表
    * @author sxmd
    * @param page:
    * @param pageSize:
    * @param map:  条件
    * @date
    */
    @Override
    public PageInfo<${table.tableNameToJavaName}ListModel> find${table.tableNameToJavaName}List(Integer page, Integer pageSize,Map<String,Object> map) {
        int limit = pageSize != null ? pageSize : 10;
        int offset = (page != null ? page : 1);
        PageHelper.startPage(offset, limit);
        List<${table.tableNameToJavaName}ListModel> list = mapper.find${table.tableNameToJavaName}List(map);
        PageInfo<${table.tableNameToJavaName}ListModel> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
    * Description:   cs 的比较
    * @author sxmd
    * @param currentCs:
    * @param cs:
    * @date
    */
    private void checkCs(String currentCs, String cs) {
        if (!StringUtils.equals(currentCs, cs)) {
        throw new CustomException(-100182, "信息已被其他用户修改");
        }
    }

}
