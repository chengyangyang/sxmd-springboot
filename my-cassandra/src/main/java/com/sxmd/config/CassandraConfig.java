package com.sxmd.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;

/**
 * Description:
 *
 * @author cy
 * @date 2019年12月19日 14:53
 * Version 1.0
 */

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

    /**
     *  空间名称
     */
    @Value("${cassandra.keyspace-name}")
    private String keyspaceName;

    /**
     * 节点IP（连接的集群节点IP）
     */
    @Value("${cassandra.contact-points}")
    private String contactPoints;

    /**
     * 端口
     */
    @Value("${cassandra.port}")
    private int port;

    /**
     * 集群名称
     */
     @Value("${cassandra.cluster-name}")
     private String clusterName;

    @Override
    protected String getKeyspaceName() {
        return this.keyspaceName;
    }

    @Override
    protected String getContactPoints() {
        return this.contactPoints;
    }

    @Override
    protected int getPort() {
        return this.port;
    }

    @Override
    protected String getClusterName() {
        return this.clusterName;
    }

    @Override
    protected boolean getMetricsEnabled() {
        return false;
    }




}
