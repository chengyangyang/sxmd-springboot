package com.sxmd.content.mytest.service;

import com.sxmd.base.BasePage;
import com.sxmd.content.mytest.model.am.MyTestAddModel;
import com.sxmd.content.mytest.model.dm.MyTestModel;
import com.sxmd.content.mytest.model.em.MyTestEditModel;
import com.sxmd.content.mytest.model.lm.MyTestListModel;

import java.util.Map;

/**
* Description: 测试表接口
*
* @author sxmd
* @date
* Version 1.0
*/
public interface MyTestService {

    /**
     * Description:  测试表-新增
     *
     * @param model:
     * @return java.lang.String
     * @author sxmd
     * @date 2019/6/12 10:30
     */
    boolean insertMyTest(MyTestAddModel model);

    /**
    * Description:  测试表-更新
    * @author sxmd
    * @param model:
    * @date
    */
    boolean updateMyTest(MyTestEditModel model);

    /**
    * Description:   测试表-删除
    * @author sxmd
    * @param id: 主键
    * @date
    */
    boolean deleteMyTest(Long id);

    /**
    * Description:   测试表-详情
    * @author sxmd
    * @param id: 主键
    * @date
    */
    MyTestModel getMyTestById(Long id);

    /**
    * Description:   测试表-列表
    * @author sxmd
    * @param pageNum: 第几页
    * @param pageSize: 显示条数
    * @param map:  条件
    * @date
    */
    BasePage<MyTestListModel> findMyTestList(Integer pageNum, Integer pageSize, Map<String, Object> map);


}
