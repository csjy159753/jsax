package com.jinhe.modules.demo.dto;


import java.util.List;

/**
 * @author Administrator
 */
public class PairChildren extends Pair {
    private List<Pair> Children;

    public List<Pair> getChildren() {
        return Children;
    }

    public void setChildren(List<Pair> children) {
        Children = children;
    }
}
