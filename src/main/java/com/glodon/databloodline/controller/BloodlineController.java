package com.glodon.databloodline.controller;

import com.alibaba.fastjson.JSON;
import com.glodon.databloodline.pojo.Dependency;
import com.glodon.databloodline.pojo.Table;
import com.glodon.databloodline.pojo.Tree;
import com.glodon.databloodline.service.DependencyService;
import com.glodon.databloodline.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wangbw-b
 * @create 2021-07-28 10:35
 */
@Controller
public class BloodlineController {

    @Autowired
    private TableService tableService;

    @Autowired
    private DependencyService dependencyService;

    // 显示所有数据血缘关系列表
    @GetMapping("/allDependencies")
    public String list(Model model) {
        List<Dependency> dependencies = dependencyService.queryAllDependencies();
        model.addAttribute("dependencies", dependencies);
        return "allDependencies";
    }

    /**
     * 判断表名是否存在，不存在则添加该表
     *
     * @param dependency 依赖
     */
    private void createTableIfNotExists(Dependency dependency) {
        Table parent = tableService.queryTableByName(dependency.getParentName());
        Table child = tableService.queryTableByName(dependency.getChildName());
        // 判断输入的表是否存在，不存在则先添加表
        if (parent == null || child == null) {
            if (parent == null) {
                parent = new Table();
                parent.setTableName(dependency.getParentName());
                tableService.addTable(parent);
            }
            if (child == null) {
                child = new Table();
                child.setTableName(dependency.getChildName());
                tableService.addTable(child);
            }
        }
    }

    @GetMapping("/toAddDependency")
    public String toAddDependency() {
        return "addDependency";
    }

    @GetMapping("/addDependency")
    public String addDependency(Dependency dependency) {
        // 若表不存在先创建
        createTableIfNotExists(dependency);
        dependencyService.addDependency(dependency);
        return "redirect:/allDependencies";
    }

    @GetMapping("/toUpdateDependency")
    public String toUpdateDependency(int id, Model model) {
        Dependency dependency = dependencyService.queryDependency(id);
        model.addAttribute("QDependency", dependency);
        return "updateDependency";
    }

    @GetMapping("/updateDependency")
    public String updateDependency(Dependency dependency) {
        // 若表不存在先创建
        createTableIfNotExists(dependency);
        dependencyService.updateDependency(dependency);
        return "redirect:/allDependencies";
    }

    @GetMapping("/deleteDependency/{dependencyId}")
    public String deleteDependency(@PathVariable("dependencyId") int id) {
        dependencyService.deleteDependency(id);
        return "redirect:/allDependencies";
    }

    @GetMapping("/queryParentDependency")
    public String queryParentDependency(String queryTableName, Model model) {
        List<Dependency> dependencies = dependencyService.queryParentDependenciesByName(queryTableName);
        if (dependencies.size() == 0) {
            model.addAttribute("error", "该表没有父依赖！");
        } else {
            model.addAttribute("dependencies", dependencies);
        }
        model.addAttribute("tableName", queryTableName);
        return "allDependencies";
    }

    @GetMapping("/queryChildDependency")
    public String queryChildDependency(String queryTableName, Model model) {
        List<Dependency> dependencies = dependencyService.queryChildDependenciesByName(queryTableName);
        if (dependencies.size() == 0) {
            model.addAttribute("error", "该表没有子依赖！");
        } else {
            model.addAttribute("dependencies", dependencies);
        }
        model.addAttribute("tableName", queryTableName);
        return "allDependencies";
    }

    @GetMapping("/queryAllParentsDependency")
    public String queryAllParentsDependency(String queryTableName, Model model) {
        List<Dependency> dependencyList = dependencyService.queryAllParentsDependenciesByName(queryTableName);
        if (dependencyList.size() == 0) {
            model.addAttribute("error", "该表没有依赖任何表！");
        } else {
            model.addAttribute("dependencies", dependencyList);
        }
        model.addAttribute("tableName", queryTableName);
        return "allDependencies";
    }

    @GetMapping("/queryAllChildrenDependency")
    public String queryAllChildrenDependency(String queryTableName, Model model) {
        List<Dependency> dependencyList = dependencyService.queryAllChildrenDependenciesByName(queryTableName);
        if (dependencyList.size() == 0) {
            model.addAttribute("error", "该表没有被任何表依赖！");
        } else {
            model.addAttribute("dependencies", dependencyList);
        }
        model.addAttribute("tableName", queryTableName);
        return "allDependencies";
    }

    @GetMapping("/childToParentsTree")
    public String childToParentsTree(String queryTableName, Model model) {
        Tree tree = dependencyService.queryAllChildToParentsByName(queryTableName);
        model.addAttribute("tableName", queryTableName);
        if (tree.getChildNames() == null) {
            model.addAttribute("error", "该表没有依赖任何表！");
            return "allDependencies";
        } else {
            String treeJson = JSON.toJSONString(tree);
            model.addAttribute("treeJson", treeJson);
            return "childToParentsTree";
        }
    }

    @GetMapping("/parentToChildrenTree")
    public String parentToChildrenTree(String queryTableName, Model model) {
        Tree tree = dependencyService.queryAllParentToChildrenByName(queryTableName);
        model.addAttribute("tableName", queryTableName);
        if (tree.getChildNames() == null) {
            model.addAttribute("error", "该表没有被任何表依赖！");
            return "allDependencies";
        } else {
            String treeJson = JSON.toJSONString(tree);
            model.addAttribute("treeJson", treeJson);
            return "parentToChildrenTree";
        }
    }
}
