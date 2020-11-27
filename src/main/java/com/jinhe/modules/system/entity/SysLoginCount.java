package com.jinhe.modules.system.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 登录统计
 * </p>
 *
 * @author rls
 * @since 2020-11-27
 */
@ApiModel(value="SysLoginCount对象", description="登录统计")
public class SysLoginCount implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "总累计登录次数")
    private Integer count;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "登录名")
    private String userName;

    @ApiModelProperty(value = "登录成功记录的用户id")
    private String userId;

    @ApiModelProperty(value = "pc登录次数")
    private Integer pcCount;

    @ApiModelProperty(value = "app登录次数")
    private Integer appCount;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getPcCount() {
        return pcCount;
    }

    public void setPcCount(Integer pcCount) {
        this.pcCount = pcCount;
    }

    public Integer getAppCount() {
        return appCount;
    }

    public void setAppCount(Integer appCount) {
        this.appCount = appCount;
    }

    @Override
    public String toString() {
        return "SysLoginCount{" +
        "id=" + id +
        ", count=" + count +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", userName=" + userName +
        ", userId=" + userId +
        ", pcCount=" + pcCount +
        ", appCount=" + appCount +
        "}";
    }
}
