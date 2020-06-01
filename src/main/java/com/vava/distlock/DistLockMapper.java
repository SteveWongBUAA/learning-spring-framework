package com.vava.distlock;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DistLockMapper {
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "resourceName", column = "resource_name"),
            @Result(property = "nodeInfo", column = "node_info"),
            @Result(property = "createAt", column = "create_at")
    })
    @Select("select * from dist_lock where resource_name=#{resourceName}")
    DistLockRepo getLockByResourceName(String resourceName);

    @Insert("insert into dist_lock (resource_name, node_info, create_at) VALUES (#{resourceName}, #{nodeInfo}, "
            + "#{createAt})")
    int insertLock(@Param("resourceName") String resourceName, @Param("nodeInfo") String nodeInfo,
            @Param("createAt") long createAt);

    @Delete("delete from dist_lock where resource_name = #{resourceName} and node_info = #{nodeInfo}")
    int deleteLock(@Param("resourceName") String resourceName, @Param("nodeInfo") String nodeInfo);
}
