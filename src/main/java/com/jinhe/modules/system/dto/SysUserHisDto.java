package com.jinhe.modules.system.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SysUserHisDto implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "用户id_主键")
    @TableId("ID")
    private String id;

    @ApiModelProperty(value = "登录名")
    @TableField("NORMALIZED_USERNAME")
    private String normalizedUsername;

    @ApiModelProperty(value = "密码")
    @TableField("PASSWORD_HASH")
    private String passwordHash;

    @ApiModelProperty(value = "昵称")
    @TableField("NICK_NAME")
    private String nickName;

    @ApiModelProperty(value = "头像地址")
    @TableField("AVATAR_URL")
    private String avatarUrl;

    @ApiModelProperty(value = "真实姓名")
    @TableField("REAL_NAME")
    private String realName;

    @ApiModelProperty(value = "关联微信id")
    @TableField("OPEN_ID")
    private String openId;

    @ApiModelProperty(value = "性别：0未知 1男 2女")
    @TableField("SEX")
    private BigDecimal sex;

    @ApiModelProperty(value = "0正常1待审核2禁用3逻辑删除")
    @TableField("STATE")
    private BigDecimal state;

    @ApiModelProperty(value = "手机号码")
    @TableField("PHONE_NUMBER")
    private String phoneNumber;

    @ApiModelProperty(value = "电子邮件")
    @TableField("NORMALIZED_EMAIL")
    private String normalizedEmail;

    @ApiModelProperty(value = "电话号码是否验证0默认未认证1已认证")
    @TableField("PHONE_NUMBER_CONFIRMED")
    private BigDecimal phoneNumberConfirmed;

    @ApiModelProperty(value = "电子邮箱是否验证0默认未认证1已认证")
    @TableField("EMAIL_CONFIRMED")
    private BigDecimal emailConfirmed;

    @ApiModelProperty(value = "锁定时间")
    @TableField("LOCK_OUT_TIME")
    private LocalDateTime lockOutTime;

    @ApiModelProperty(value = "是否启用锁定0不启用1启用")
    @TableField("LOCK_OUT_ENABLED")
    private BigDecimal lockOutEnabled;

    @ApiModelProperty(value = "访问失败统计")
    @TableField("ACCESS_FAILED_COUNT")
    private BigDecimal accessFailedCount;

    @ApiModelProperty(value = "角色类型0普通99超级管理员90管理员")
    @TableField("TYPE")
    private BigDecimal type;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "对象ID，目前用于关联企业ID")
    @TableField("OBJECT_ID")
    private String objectId;
}
