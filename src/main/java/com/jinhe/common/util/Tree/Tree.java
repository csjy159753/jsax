package com.jinhe.common.util.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 树形结构工具类
 *
 * @param
 */
public class Tree {
    public static <T> List<TreeNode> CreateTree(List<T> objs, ITree iTree) {
        List<TreeNode> list = new ArrayList<>();
        List<TreeNode> listParent = new ArrayList<>();
        for (T item : objs) {
            TreeNode treeNode = iTree.modelTo(item);
            list.add(treeNode);

        }
        for (TreeNode nolde : list) {
//            List<TreeNode> cf=  list.stream().filter(a -> a.getId().equals(nolde.getParentId())).collect(Collectors.toList());
            boolean bool = list.stream().anyMatch(a -> a.getId().equals(nolde.getParentId()));
            if (!bool) {
                listParent.add(nolde);
            }
        }
        for (TreeNode t : listParent) {
            t.setChildren(getChildren(list, t.getId()));
        }
        return listParent;
    }

    public static <T> List<TreeNode> getChildren(List<TreeNode> list, String id) {
        List<TreeNode> listData = new ArrayList<>();
        for (TreeNode node : list) {
            if (id.equals(node.getParentId())) {
                node.setChildren(getChildren(list, node.getId()));
                listData.add(node);
            }
        }
        return listData;
    }
}
