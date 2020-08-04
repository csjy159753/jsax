package com.jinhe.modules.system.dto;

import lombok.Data;

import java.util.List;

@Data
public class RegionTreeNode<T> {
    private String Code;
    private String parentCode;
    private T nodeValue ;
    private List<RegionTreeNode<T>> children;
    private List<String> path;
    private int depth;

}
