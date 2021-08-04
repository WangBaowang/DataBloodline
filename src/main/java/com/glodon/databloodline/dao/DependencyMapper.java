package com.glodon.databloodline.dao;

import com.glodon.databloodline.pojo.Dependency;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wangbw-b
 * @create 2021-07-27 15:15
 */
@Mapper
public interface DependencyMapper {
    int addDependency(Dependency dependency);
    int deleteDependency(@Param("dependencyId") int dependencyId);
    int updateDependency(Dependency dependency);
    Dependency queryDependency(@Param("dependencyId") int dependencyId);
    List<Dependency> queryAllDependencies();

    /**
     * 查看所有的父依赖表
     *
     * @param tableName 表名
     * @return 父依赖表
     */
    List<Dependency> queryParentDependenciesByName(@Param("tableName") String tableName);

    /**
     * 查看所有的子依赖表
     *
     * @param tableName 表名
     * @return 子依赖表
     */
    List<Dependency> queryChildDependenciesByName(@Param("tableName") String tableName);
}
