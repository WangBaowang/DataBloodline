package com.glodon.databloodline.pojo;

import lombok.Data;

/**
 * @author wangbw-b
 * @create 2021-07-27 15:05
 */
@Data
public class Dependency {
    private int id;
    private int parentId;
    private String parentName;
    private int childId;
    private String childName;
}
