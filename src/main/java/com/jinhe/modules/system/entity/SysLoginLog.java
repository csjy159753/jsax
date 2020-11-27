package com.jinhe.modules.system.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 登录统计日志
 * </p>
 *
 * @author rls
 * @since 2020-11-27
 */
@ApiModel(value="SysLoginLog对象", description="登录统计日志")
public class SysLoginLog implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "类型 0未知 1手机登录 2pc登录3微信登录4授权码登录")
    private Integer type;

    @ApiModelProperty(value = "登录名")
    private String userName;

    @ApiModelProperty(value = "登录成功记录的用户id")
    private String userId;

    @ApiModelProperty(value = "客户端ip")
    private String clientIp;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    @Override
    public String toString() {
        return "SysLoginLog{" +
        "id=" + id +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", type=" + type +
        ", userName=" + userName +
        ", userId=" + userId +
        ", clientIp=" + clientIp +
        "}";
    }
}
