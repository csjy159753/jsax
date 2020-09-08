package com.jinhe.modules.system.entity;

import com.jinhe.common.util.Tree.TreeNode;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class IRoleTreeNode<T> {
    private String id;
    private String parentId;
    private String name;
    private String tag;
    private String description;//数据库的tag
    private Integer type;
    private Integer state;
    private Integer sort;
    private Integer lockoutEnabled;//是否锁定
    private Date lockoutTime;//锁定时间
    private String operatorId;
    private List<IRoleTreeNode<T>> children;


}
