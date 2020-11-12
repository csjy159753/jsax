package com.jinhe.modules.system.dto;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

/**
 * author:xk
 * date:2020.04.15
 *
 * @author Administrator
 */
public class SysResourceDTO implements Serializable {


    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId("ID")
    private String id;

    /**
     * 父键id
     */
    @TableField("PARENT_ID")
    private String parentId;

    /**
     * 英文名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 显示名称
     */
    @TableField("DISPLAY_NAME")
    private String displayName;

    /**
     * 描述
     */
    @TableField("NOTE")
    private String note;

    /**
     * 状态0正常1禁用
     */
    @TableField("STATE")
    private Integer state;

    /**
     * 类型（预留）
     */
    @TableField("TYPE")
    private Integer type;

    /**
     * 排序
     */
    @TableField("SORT")
    private Integer sort;

    /**
     * url路径
     */
    @TableField("PATH")
    private String path;

    /**
     * 小图标
     */
    @TableField("ICON")
    private String icon;

    /**
     * 创建者id
     */
    @TableField("CREATE_USER_ID")
    private String createUserId;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")

    private Date createTime;

    /**
     * 更新
     */
    @TableField("UPDATE_TIME")

    private Date updateTime;

    /**
     * 显示类型
     */
    @TableField("DISPLAY_TYPE")
    private Integer displayType;

    private String resourceItemIds;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDisplayType() {
        return displayType;
    }

    public void setDisplayType(Integer displayType) {
        this.displayType = displayType;
    }

    public String getResourceItemIds() {
        return resourceItemIds;
    }

    public void setResourceItemIds(String resourceItemIds) {
        this.resourceItemIds = resourceItemIds;
    }
}
