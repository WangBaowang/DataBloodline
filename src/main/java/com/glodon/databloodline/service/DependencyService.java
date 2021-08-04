package com.glodon.databloodline.service;

import com.glodon.databloodline.pojo.Dependency;
import com.glodon.databloodline.pojo.Tree;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wangbw-b
 * @create 2021-07-27 16:31
 */
public interface DependencyService {
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

    /**
     * 查看所有的依赖表
     *
     * @param childTableName 子表名
     * @return 所有依赖表
     */
    List<Dependency> queryAllParentsDependenciesByName(@Param("childTableName") String childTableName);

    /**
     * 查看所有被依赖表
     *
     * @param parentTableName 父表名
     * @return 所有被依赖表
     */
    List<Dependency> queryAllChildrenDependenciesByName(@Param("parentTableName") String parentTableName);

    /**
     * 树形查看所有的依赖表名
     *
     * @param childTableName 子表名
     * @return 所有的子--父们键值对
     */
    Tree queryAllChildToParentsByName(@Param("childTableName") String childTableName);

    /**
     * 树形查看所有的被依赖表名
     *
     * @param parentTableName 父表名
     * @return 所有的父--子们键值对
     */
    Tree queryAllParentToChildrenByName(@Param("parentTableName") String parentTableName);
}
