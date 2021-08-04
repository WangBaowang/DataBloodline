package com.glodon.databloodline.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author wangbw-b
 * @create 2021-07-29 10:45
 */
@Data
public class Tree {
    private String parentName;
    private List<Tree> childNames;
}
