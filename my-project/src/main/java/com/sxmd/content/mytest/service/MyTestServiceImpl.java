package com.sxmd.content.mytest.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sxmd.base.PageResult;
import com.sxmd.content.mytest.dao.MyTestDao;
import com.sxmd.content.mytest.entity.MyTest;
import com.sxmd.content.mytest.model.am.MyTestAddModel;
import com.sxmd.content.mytest.model.dm.MyTestModel;
import com.sxmd.content.mytest.model.em.MyTestEditModel;
import com.sxmd.content.mytest.model.lm.MyTestListModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
* Description: 测试表 实现类
*
* @author sxmd
* @date
* Version 1.0
*/
@Service
public class MyTestServiceImpl implements MyTestService {

    @Resource
    MyTestDao dao;

    /**
    * Description:   新增
    * @author sxmd
    * @param model:
    * @date
    */
    @Override
    public boolean addMyTest(MyTestAddModel model) {
        MyTest entity = new MyTest();
        BeanUtils.copyProperties(model, entity);
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
    public boolean updateMyTest(MyTestEditModel model) {
        MyTest entity = dao.selectByPrimaryKey(model);
        BeanUtils.copyProperties(model, entity);
        int result = dao.updateByPrimaryKey(entity);
        return result > 0 ? true : false;
    }

    /**
    * Description: 删除
    * @author sxmd
    * @param id: 主键
    * @date
    */
    @Override
    public boolean deleteMyTest(String id) {
        int result = dao.deleteByPrimaryKey(id);
        return result > 0 ? true : false;
    }


    /**
    * Description:   详情
    * @author sxmd
    * @param id: 主键
    * @date
    */
    @Override
    public MyTestModel getMyTestById(String id) {
        MyTest entity = dao.selectByPrimaryKey(id);
        MyTestModel model = new MyTestModel();
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
    public PageResult<MyTestListModel> findMyTestList(Integer page, Integer pageSize, Map<String,Object> map) {
        List<MyTestListModel> list = dao.findMyTestList(map);
        PageResult<MyTestListModel> result = new PageResult<>(page,pageSize,list);
        return result;
    }


}
