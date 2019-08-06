package com.sxmd.content.mytest.dao;

import com.sxmd.base.BaseDao;
import com.sxmd.content.mytest.entity.MyTest;
import com.sxmd.content.mytest.model.lm.MyTestListModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* Description: 测试表 dao接口
*
* @author sxmd
* @date
* Version 1.0
*/
@Mapper
public interface MyTestDao extends BaseDao<MyTest> {

    /**
    * Description: 列表查询
    * @author sxmd
    * @param map:
    * @return java.util.List
    * @date
    */
    List<MyTestListModel> findMyTestList(@Param("map") Map<String, Object> map);
}