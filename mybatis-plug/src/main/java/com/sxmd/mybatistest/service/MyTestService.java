package com.sxmd.mybatistest.service;

import com.sxmd.mybatistest.dao.MyTestDao;
import com.sxmd.mybatistest.entity.MyTestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 *
 * @author cy
 * @date 2019年06月21日 11:36
 * Version 1.0
 */
@Service
public class MyTestService {

    @Autowired
    private MyTestDao myTestDao;

    public MyTestEntity getMytest(MyTestEntity entity){
        return myTestDao.selectOne(entity);
    }

    public List<MyTestEntity> getMytestAll(){
        return myTestDao.selectAll();
    }


}
