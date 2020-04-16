package com.jinhe.common.util.Tree;

import lombok.Data;

import java.util.List;

@Data
public class TreeNode<T> {
    private String id;
    private String parentId;
    private T nodeValue ;
    private List<TreeNode<T>> children;
    private List<String> path;
    private int depth;

}
