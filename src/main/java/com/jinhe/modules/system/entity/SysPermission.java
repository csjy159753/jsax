package com.jinhe.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 角色授权
 * </p>
 *
 * @author rls
 * @since 2020-11-27
 */
@ApiModel(value="SysPermission对象", description="角色授权")
public class SysPermission implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键id")
    private String id;

    @ApiModelProperty(value = "父id")
    private String parentId;

    @ApiModelProperty(value = "创建者")
    private String createUserId;

    @ApiModelProperty(value = "状态0正常1禁用")
    private Integer state;

    @ApiModelProperty(value = "角色id")
    private String roleId;

    @ApiModelProperty(value = "资源id")
    private String resourceId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "子对象数量")
    @TableField("children_num")
    private Integer childrenNum;


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

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getChildrenNum() {
        return childrenNum;
    }

    public void setChildrenNum(Integer childrenNum) {
        this.childrenNum = childrenNum;
    }

    @Override
    public String toString() {
        return "SysPermission{" +
        "id=" + id +
        ", parentId=" + parentId +
        ", createUserId=" + createUserId +
        ", state=" + state +
        ", roleId=" + roleId +
        ", resourceId=" + resourceId +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", childrenNum=" + childrenNum +
        "}";
    }
}
