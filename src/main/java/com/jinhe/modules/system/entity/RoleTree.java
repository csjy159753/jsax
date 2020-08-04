package com.jinhe.modules.system.entity;

import com.jinhe.common.util.Tree.ITree;
import com.jinhe.common.util.Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形结构工具类
 *
 * @param
 */
public class RoleTree {
    public static <T> List<IRoleTreeNode> CreateTree(List<T> objs, IRoleTree iTree) {
        List<IRoleTreeNode> list = new ArrayList<>();
        List<IRoleTreeNode> listParent = new ArrayList<>();
        for (T item : objs) {
            IRoleTreeNode treeNode = iTree.modelTo(item);
            list.add(treeNode);

        }
        for (IRoleTreeNode nolde : list) {
//            List<TreeNode> cf=  list.stream().filter(a -> a.getId().equals(nolde.getParentId())).collect(Collectors.toList());
            boolean bool = list.stream().anyMatch(a -> a.getId().equals(nolde.getParentId()));
            if (!bool) {
                listParent.add(nolde);
            }
        }
        for (IRoleTreeNode t : listParent) {
            t.setChildren(getChildren(list, t.getId()));
        }
        return listParent;
    }

    public static <T> List<IRoleTreeNode> getChildren(List<IRoleTreeNode> list, String id) {
        List<IRoleTreeNode> listData = new ArrayList<>();
        for (IRoleTreeNode node : list) {
            if (id.equals(node.getParentId())) {
                node.setChildren(getChildren(list, node.getId()));
                listData.add(node);
            }
        }
        return listData;
    }
}
