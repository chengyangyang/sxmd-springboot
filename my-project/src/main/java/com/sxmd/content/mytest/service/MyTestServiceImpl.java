package com.sxmd.content.mytest.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sxmd.content.mytest.dao.MyTestDao;
import com.sxmd.content.mytest.entity.MyTest;
import com.sxmd.content.mytest.model.am.MyTestAddModel;
import com.sxmd.content.mytest.model.dm.MyTestModel;
import com.sxmd.content.mytest.model.em.MyTestEditModel;
import com.sxmd.content.mytest.model.lm.MyTestListModel;
import org.apache.commons.lang3.StringUtils;
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
    public String updateMyTest(MyTestEditModel model) {
        int i = dao.updateByPrimaryKey();
        // 查询当前数据
        MyTest entity = dao.selectByKeyIfNullThrowError(model.getPkid());
        // 校验
        checkCs(entity.getCs(), model.getCs());
        BeanUtils.copyProperties(model, entity);
        return dao.updateAll(entity);
    }

    /**
    * Description: 删除
    * @author sxmd
    * @param id: 主键
    * @date
    */
    @Override
    public String deleteMyTest(String id) {
        dao.selectByKeyIfNullThrowError(id);
        return this.delete(id);
    }


    /**
    * Description:   详情
    * @author sxmd
    * @param id: 主键
    * @date
    */
    @Override
    public MyTestModel getEMyTestById(String id) {
        MyTest entity = dao.selectByKeyIfNullThrowError(id);
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
    public PageInfo<MyTestListModel> findMyTestList(Integer page, Integer pageSize, Map<String,Object> map) {
        int limit = pageSize != null ? pageSize : 10;
        int offset = (page != null ? page : 1);
        PageHelper.startPage(offset, limit);
        List<MyTestListModel> list = dao.findMyTestList(map);
        PageInfo<MyTestListModel> pageInfo = new PageInfo<>(list);
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
