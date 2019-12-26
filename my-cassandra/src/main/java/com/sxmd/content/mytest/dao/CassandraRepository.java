package com.sxmd.content.mytest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Repository;

/**
 * Description:
 *
 * @author cy
 * @date 2019年12月19日 15:49
 * Version 1.0
 */
@Repository
public class CassandraRepository {

    @Autowired
    private CassandraTemplate cassandraTemplate;



}
