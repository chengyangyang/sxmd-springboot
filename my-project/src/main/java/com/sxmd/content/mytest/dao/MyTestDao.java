package com.sxmd.content.mytest.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sxmd.content.mytest.entity.MyTest;
import com.sxmd.content.mytest.model.lm.MyTestListModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface MyTestDao extends BaseMapper<MyTest> {

    List<MyTestListModel> findMyTestList(@Param("map") Map<String, Object> map);

}
