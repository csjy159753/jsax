package com.jinhe.modules.system.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author rls
 * @since 2020-11-27
 */
@ApiModel(value="SysUser对象", description="用户")
public class SysUser implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "用户id")
    private String id;

    @ApiModelProperty(value = "登录名")
    private String normalizedUsername;

    @ApiModelProperty(value = "密码")
    private String passwordHash;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "头像地址")
    private String avatarUrl;

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "openid")
    private String openId;

    @ApiModelProperty(value = "性别0未知1男2女")
    private Integer sex;

    @ApiModelProperty(value = "0正常1待审核2禁用3逻辑删除")
    private Integer state;

    @ApiModelProperty(value = "手机号码")
    private String phoneNumber;

    @ApiModelProperty(value = "电话号码是否验证0默认未认证1已认证")
    private Integer phoneNumberConfirmed;

    @ApiModelProperty(value = "电子邮件")
    private String normalizedEmail;

    @ApiModelProperty(value = "电子邮箱是否验证0默认未认证1已认证")
    private Integer emailConfirmed;

    @ApiModelProperty(value = "锁定时间")
    private LocalDateTime lockOutTime;

    @ApiModelProperty(value = "是否启用锁定0不启用1启用")
    private Integer lockOutEnabled;

    @ApiModelProperty(value = "访问失败统计")
    private Integer accessFailedCount;

    @ApiModelProperty(value = "角色类型 0普通 99 超级管理员90管理员")
    private Integer type;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "备注")
    private String note;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNormalizedUsername() {
        return normalizedUsername;
    }

    public void setNormalizedUsername(String normalizedUsername) {
        this.normalizedUsername = normalizedUsername;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getPhoneNumberConfirmed() {
        return phoneNumberConfirmed;
    }

    public void setPhoneNumberConfirmed(Integer phoneNumberConfirmed) {
        this.phoneNumberConfirmed = phoneNumberConfirmed;
    }

    public String getNormalizedEmail() {
        return normalizedEmail;
    }

    public void setNormalizedEmail(String normalizedEmail) {
        this.normalizedEmail = normalizedEmail;
    }

    public Integer getEmailConfirmed() {
        return emailConfirmed;
    }

    public void setEmailConfirmed(Integer emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    public LocalDateTime getLockOutTime() {
        return lockOutTime;
    }

    public void setLockOutTime(LocalDateTime lockOutTime) {
        this.lockOutTime = lockOutTime;
    }

    public Integer getLockOutEnabled() {
        return lockOutEnabled;
    }

    public void setLockOutEnabled(Integer lockOutEnabled) {
        this.lockOutEnabled = lockOutEnabled;
    }

    public Integer getAccessFailedCount() {
        return accessFailedCount;
    }

    public void setAccessFailedCount(Integer accessFailedCount) {
        this.accessFailedCount = accessFailedCount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "SysUser{" +
        "id=" + id +
        ", normalizedUsername=" + normalizedUsername +
        ", passwordHash=" + passwordHash +
        ", nickName=" + nickName +
        ", avatarUrl=" + avatarUrl +
        ", realName=" + realName +
        ", openId=" + openId +
        ", sex=" + sex +
        ", state=" + state +
        ", phoneNumber=" + phoneNumber +
        ", phoneNumberConfirmed=" + phoneNumberConfirmed +
        ", normalizedEmail=" + normalizedEmail +
        ", emailConfirmed=" + emailConfirmed +
        ", lockOutTime=" + lockOutTime +
        ", lockOutEnabled=" + lockOutEnabled +
        ", accessFailedCount=" + accessFailedCount +
        ", type=" + type +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", note=" + note +
        "}";
    }
}
