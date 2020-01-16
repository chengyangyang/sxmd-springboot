
package com.sxmd.content.mytest.service;

import com.github.pagehelper.PageHelper;
import com.sxmd.base.BasePage;
import com.sxmd.content.mytest.dao.MyTestDao;
import com.sxmd.content.mytest.entity.MyTest;
import com.sxmd.content.mytest.model.am.MyTestAddModel;
import com.sxmd.content.mytest.model.dm.MyTestModel;
import com.sxmd.content.mytest.model.em.MyTestEditModel;
import com.sxmd.content.mytest.model.lm.MyTestListModel;
import com.sxmd.utils.IdWorkerUil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Description: 测试表 实现类
 *
 * @author sxmd
 * @date Version 1.0
 */

@Service
public class MyTestServiceImpl implements MyTestService {

    @Resource
    MyTestDao dao;


    /**
     * Description:   新增
     *
     * @param model:
     * @author sxmd
     * @date
     */
    @Override
    public boolean insertMyTest(MyTestAddModel model) {
        MyTest entity = new MyTest();
        BeanUtils.copyProperties(model, entity);
        entity.setId(IdWorkerUil.generateId());
        int result = dao.insert(entity);
        return result > 0 ? true : false;
    }


    /**
     * Description: 更新
     *
     * @param model:
     * @author sxmd
     * @date
     */
    @Override
    public boolean updateMyTest(MyTestEditModel model) {
        MyTest entity = dao.selectById(model.getId());
        BeanUtils.copyProperties(model, entity);
        int result = dao.updateById(entity);
        return result > 0 ? true : false;
    }


    /**
     * Description: 删除
     *
     * @param id: 主键
     * @author sxmd
     * @date
     */
    @Override
    public boolean deleteMyTest(Long id) {
        int result = dao.deleteById(id);
        return result > 0 ? true : false;
    }


    /**
     * Description:   详情
     *
     * @param id: 主键
     * @author sxmd
     * @date
     */
    @Override
    public MyTestModel getMyTestById(Long id) {
        MyTest entity = dao.selectById(id);
        MyTestModel model = new MyTestModel();
        BeanUtils.copyProperties(entity, model);
        return model;
    }


    /**
     * Description: 列表
     *
     * @param pageNum:
     * @param pageSize:
     * @param map:      条件
     * @author sxmd
     * @date
     */
    @Override
    public BasePage<MyTestListModel> findMyTestList(Integer pageNum, Integer pageSize, Map<String, Object> map) {
        // 使用pagehelp 进行分页
        PageHelper.startPage(pageNum,pageSize);
        List<MyTestListModel> list = dao.findMyTestList(map);
        return new BasePage<>(list);
    }

}

