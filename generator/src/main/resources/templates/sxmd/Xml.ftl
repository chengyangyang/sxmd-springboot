<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.sxmd.content.${folderName!''}.dao.${table.tableNameToJavaName}Dao">

    <sql id="base_column">

    </sql>


    <select id="find${table.tableNameToJavaName}List" resultType="com.sxmd.content.${folderName!''}.model.lm.${table.tableNameToJavaName}ListModel">
        select
        <include refid="base_column"></include> *
        from ${table.tableName}
    </select>

</mapper>