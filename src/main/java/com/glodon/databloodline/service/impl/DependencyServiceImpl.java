package com.glodon.databloodline.service.impl;

import com.glodon.databloodline.dao.DependencyMapper;
import com.glodon.databloodline.dao.TableMapper;
import com.glodon.databloodline.pojo.Dependency;
import com.glodon.databloodline.pojo.Tree;
import com.glodon.databloodline.service.DependencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangbw-b
 * @create 2021-07-27 16:44
 */
@Service
public class DependencyServiceImpl implements DependencyService {

    @Autowired
    private TableMapper tableMapper;

    @Autowired
    private DependencyMapper dependencyMapper;

    @Override
    public int addDependency(Dependency dependency) {
        int parentId = tableMapper.queryTableByName(dependency.getParentName()).getId();
        int childId = tableMapper.queryTableByName(dependency.getChildName()).getId();
        dependency.setParentId(parentId);
        dependency.setChildId(childId);
        return dependencyMapper.addDependency(dependency);
    }

    @Override
    public int deleteDependency(int dependencyId) {
        return dependencyMapper.deleteDependency(dependencyId);
    }

    @Override
    public int updateDependency(Dependency dependency) {
        int parentId = tableMapper.queryTableByName(dependency.getParentName()).getId();
        int childId = tableMapper.queryTableByName(dependency.getChildName()).getId();
        dependency.setParentId(parentId);
        dependency.setChildId(childId);
        return dependencyMapper.updateDependency(dependency);
    }

    @Override
    public Dependency queryDependency(int dependencyId) {
        return dependencyMapper.queryDependency(dependencyId);
    }

    @Override
    public List<Dependency> queryAllDependencies() {
        return dependencyMapper.queryAllDependencies();
    }

    @Override
    public List<Dependency> queryParentDependenciesByName(String tableName) {
        return dependencyMapper.queryParentDependenciesByName(tableName);
    }

    @Override
    public List<Dependency> queryChildDependenciesByName(String tableName) {
        return dependencyMapper.queryChildDependenciesByName(tableName);
    }

    @Override
    public List<Dependency> queryAllParentsDependenciesByName(String childTableName) {
        List<Dependency> dependencies = new ArrayList<>();
        recurParentDependencies(dependencies, childTableName);
        return dependencies;
    }

    private void recurParentDependencies(List<Dependency> dependencies, String childTableName) {
        List<Dependency> parents = queryParentDependenciesByName(childTableName);
        if (parents != null) {
            for (Dependency parent : parents) {
                String parentTableName = tableMapper.queryTable(parent.getParentId()).getTableName();
                if (!dependencies.contains(parent)) {
                    dependencies.add(parent);
                    recurParentDependencies(dependencies, parentTableName);
                }
            }
        }
    }

    @Override
    public List<Dependency> queryAllChildrenDependenciesByName(String parentTableName) {
        List<Dependency> dependencies = new ArrayList<>();
        recurChildDependencies(dependencies, parentTableName);
        return dependencies;
    }

    private void recurChildDependencies(List<Dependency> dependencies, String parentTableName) {
        List<Dependency> children = queryChildDependenciesByName(parentTableName);
        if (children != null) {
            for (Dependency child : children) {
                String childTableName = tableMapper.queryTable(child.getChildId()).getTableName();
                if (!dependencies.contains(child)) {
                    dependencies.add(child);
                    recurChildDependencies(dependencies, childTableName);
                }
            }
        }
    }

    @Override
    public Tree queryAllChildToParentsByName(String childTableName) {
        Tree tree = new Tree();
        // 设置根
        tree.setParentName(childTableName);
        // 查询所有父依赖
        List<Dependency> dependencies = queryParentDependenciesByName(childTableName);
        // 存放子树集合
        List<Tree> parents = new ArrayList<>();
        if (dependencies.size() != 0) {
            // 依次递归所有子树，并添加到当前的子树集合中
            for (int i = 0; i < dependencies.size(); i++) {
                Tree parent = queryAllChildToParentsByName(dependencies.get(i).getParentName());
                parents.add(parent);
            }
            tree.setChildNames(parents);
        }
        return tree;
    }

    @Override
    public Tree queryAllParentToChildrenByName(String parentTableName) {
        Tree tree = new Tree();
        // 设置根
        tree.setParentName(parentTableName);
        // 查询所有子依赖
        List<Dependency> dependencies = queryChildDependenciesByName(parentTableName);
        // 存放子树集合
        List<Tree> children = new ArrayList<>();
        if (dependencies.size() != 0) {
            // 依次递归所有子树，并添加到当前的子树集合中
            for (int i = 0; i < dependencies.size(); i++) {
                Tree parent = queryAllParentToChildrenByName(dependencies.get(i).getChildName());
                children.add(parent);
            }
            // 设置子树
            tree.setChildNames(children);
        }
        return tree;
    }
}
