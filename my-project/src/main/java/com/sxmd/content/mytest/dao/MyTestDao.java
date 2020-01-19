package com.sxmd.content.mytest.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxmd.content.mytest.entity.MyTestEntity;
import com.sxmd.content.mytest.model.lm.MyTestListModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface MyTestDao extends BaseMapper<MyTestEntity> {

    /**
     * Description:
     *
     * @param map:
     * @return java.util.List<com.sxmd.content.mytest.model.lm.MyTestListModel>
     * @author cy
     * @date  2020/1/19 11:10
     */
    List<MyTestListModel> findMyTestList(@Param("map") Map<String, Object> map);

}
