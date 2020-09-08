package com.jinhe.modules.system.entity;

import com.jinhe.common.util.Tree.TreeNode;

public interface IRoleTree<T> {
   IRoleTreeNode<T> modelTo(T t);
}
