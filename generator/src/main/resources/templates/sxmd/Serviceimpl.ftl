package com.sxmd.content.${folderName!''}.service;

import com.github.pagehelper.PageHelper;
import com.sxmd.base.BasePage;
import com.sxmd.base.ResponseCodeEnum;
import com.sxmd.exception.SxmdException;
import com.sxmd.utils.IdWorkerUil;

import com.sxmd.content.${folderName!''}.dao.${table.tableNameToJavaName}Dao;
import com.sxmd.content.${folderName!''}.entity.${table.tableNameToJavaName}Entity;
import com.sxmd.content.${folderName!''}.model.am.${table.tableNameToJavaName}AddModel;
import com.sxmd.content.${folderName!''}.model.dm.${table.tableNameToJavaName}Model;
import com.sxmd.content.${folderName!''}.model.em.${table.tableNameToJavaName}EditModel;
import com.sxmd.content.${folderName!''}.model.lm.${table.tableNameToJavaName}ListModel;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Description: ${table.tableComment!''} 实现类
 *
 * @author sxmd
 * @date
 * Version 1.0
 */
@Service
public class ${fileName} implements ${table.tableNameToJavaName}Service {

    @Resource
    ${table.tableNameToJavaName}Dao dao;

    /**
     * Description:   新增
     * @author sxmd
     * @param model:
     * @date
     */
    @Override
    public boolean insert${table.tableNameToJavaName}(${table.tableNameToJavaName}AddModel model) {
        ${table.tableNameToJavaName}Entity entity = new ${table.tableNameToJavaName}Entity();
        BeanUtils.copyProperties(model, entity);
        entity.setId(IdWorkerUil.generateId());
        entity.setCreateDate(LocalDateTime.now());
        int result = dao.insert(entity);
        return result > 0 ? true : false;
    }

    /**
     * Description: 更新
     * @author sxmd
     * @param model:
     * @date
     */
    @Override
    public boolean update${table.tableNameToJavaName}(${table.tableNameToJavaName}EditModel model) {
        // 查询当前数据
        ${table.tableNameToJavaName}Entity entity = dao.selectById(model.getId());
        if(Objects.isNull(entity)){
            throw new SxmdException(ResponseCodeEnum.CODE_9991);
        }
        BeanUtils.copyProperties(model, entity);
        entity.setUpdateDate(LocalDateTime.now());
        int result = dao.updateById(entity);
        return result > 0 ? true : false;
    }

    /**
     * Description: 删除
     * @author sxmd
     * @param id: 主键
     * @date
     */
    @Override
    public Boolean delete${table.tableNameToJavaName}(Long id) {
        ${table.tableNameToJavaName}Entity entity = dao.selectById(id);
        if(Objects.isNull(entity)){
            throw new SxmdException(ResponseCodeEnum.CODE_9991);
        }
        int result = dao.deleteById(id);
        return result > 0 ? true : false;
    }


    /**
     * Description:   详情
     * @author sxmd
     * @param id: 主键
     * @date
     */
    @Override
    public ${table.tableNameToJavaName}Model get${table.tableNameToJavaName}ById(Long id) {
        ${table.tableNameToJavaName}Entity entity = dao.selectById(id);
        if(Objects.isNull(entity)){
            throw new SxmdException(ResponseCodeEnum.CODE_9991);
        }
        ${table.tableNameToJavaName}Model model = new ${table.tableNameToJavaName}Model();
        BeanUtils.copyProperties(entity,model);
        return model;
    }


    /**
     * Description: 列表
     * @author sxmd
     * @param pageNum:
     * @param pageSize:
     * @param map:  条件
     * @date
     */
    @Override
    public BasePage<${table.tableNameToJavaName}ListModel> find${table.tableNameToJavaName}List(Integer pageNum, Integer pageSize,Map<String,Object> map) {
        // 使用pagehelp 进行分页
        PageHelper.startPage(pageNum,pageSize);
        List<${table.tableNameToJavaName}ListModel> list = dao.find${table.tableNameToJavaName}List(map);
        return new BasePage<>(list);
    }

}
