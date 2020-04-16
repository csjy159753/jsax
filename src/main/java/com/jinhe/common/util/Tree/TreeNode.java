package com.jinhe.common.util.Tree;

import lombok.Data;

import java.util.List;

@Data
public class TreeNode<T> {
    public String id;
    public String parentId;
    public T nodeValue ;
    public List<TreeNode<T>> children;
    private List<String> path;
    public int depth;

}
