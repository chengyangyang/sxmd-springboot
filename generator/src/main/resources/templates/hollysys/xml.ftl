<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.hollysys.smartfactory.account.system.dao.mapper.${table.tableNameToJavaName}Mapper">



    <sql id="Base_Column_List">

    </sql>

    <select id="find${table.tableNameToJavaName}" resultType="com.hollysys.smartfactory.account.system.model.vm.${table.tableNameToJavaName}ListModel">
        select
        <include refid="Base_Column_List"></include> *
        from ${table.tableName}
    </select>

</mapper>