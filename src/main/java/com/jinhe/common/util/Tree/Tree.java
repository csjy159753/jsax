package com.jinhe.common.util.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形结构工具类
 * @param <T>
 */
public class Tree<T> {
    public List<TreeNode> CreateTree(List<T> objs, ITree iTree) {
        List<TreeNode> li = new ArrayList<>();
        List<TreeNode> list = new ArrayList<>();
        List<TreeNode> listData = new ArrayList<>();
        for (T item : objs) {
            TreeNode treeNode = iTree.modelTo(item);
            list.add(treeNode);
            li.add(treeNode);
        }
        for (TreeNode nolde : list) {
            li.removeIf(x -> x.equals(nolde.parentId));
        }
        for (TreeNode t : li) {
            t.setChildren(getChildren(list, t.id));
        }
        return li;
    }
    public List<TreeNode> getChildren(List<TreeNode> list, String id) {
        List<TreeNode> listData = new ArrayList<>();
        for (TreeNode node : list) {
            if (id.equals(node.parentId)) {
                node.setChildren(getChildren(list, node.id));
                listData.add(node);
            }
        }
        return listData;
    }
}
