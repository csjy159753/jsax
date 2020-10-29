package com.jinhe.modules.system.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysUser对象", description="用户")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

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


}
