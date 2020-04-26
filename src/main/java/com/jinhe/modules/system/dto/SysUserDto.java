package com.jinhe.modules.system.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class SysUserDto  {
    /**
     * 用户id
     */
    @ApiModelProperty(value="用户id",name="",example="123")
    private String id;

    /**
     * 登录名
     */
    @ApiModelProperty(value="登录名",name="normalizedUsername",example="admin")
    private String normalizedUsername;

    /**
     * 密码
     */
    @ApiModelProperty(value="密码",name="passwordHash",example="1234")
    private String passwordHash;

    /**
     * 昵称
     */
    @ApiModelProperty(value="昵称",name="nickName",example="小明")
    private String nickName;

    /**
     * 头像地址
     */
    @ApiModelProperty(value="头像地址",name="avatarUrl",example="1")
    private String avatarUrl;

    /**
     * 真实姓名
     */
    @ApiModelProperty(value="真实姓名",name="realName",example="王某")
    private String realName;

    /**
     * opendi
     */
    @ApiModelProperty(value="opend",name="openId",example="12")
    private String openId;

    /**
     * 性别0未知1男2女
     */
    @ApiModelProperty(value="性别0未知1男2女",name="sex",example="1")
    private Integer sex;

    /**
     * 0正常1待审核2禁用3逻辑删除
     */
    @ApiModelProperty(value="状态 0正常1待审核2禁用3逻辑删除",name="state",example="0")
    private Integer state;

    /**
     * 手机号码
     */
    @ApiModelProperty(value="手机号码",name="phoneNumber",example="1")
    private String phoneNumber;

    /**
     * 电子邮件
     */
    @ApiModelProperty(value="电子邮件",name="normalizedEmail",example="admin")
    private String normalizedEmail;

    /**
     * 电话号码是否验证0默认未认证1已认证
     */
    @ApiModelProperty(value="电话号码是否验证0默认未认证1已认证",name="phoneNumberConfirmed",example="0")
    private Integer phoneNumberConfirmed;

    /**
     * 电子邮箱是否验证0默认未认证1已认证
     */
    @ApiModelProperty(value="电子邮箱是否验证0默认未认证1已认证",name="emailConfirmed",example="1")
    private Integer emailConfirmed;

    /**
     * 锁定时间
     */
    @ApiModelProperty(value="锁定时间",name="lockOutTime",example="1")
    private Date lockOutTime;

    /**
     * 是否启用锁定0不启用1启用
     */
    @ApiModelProperty(value="是否启用锁定0不启用1启用",name="lockOutEnabled",example="1")
    private Integer lockOutEnabled;

    /**
     * 访问失败统计
     */
    @ApiModelProperty(value="访问失败统计",name="accessFailedCount",example="2")
    private Integer accessFailedCount;

    /**
     * 角色类型0普通99超级管理员90管理员
     */
    @ApiModelProperty(value="角色类型0普通99超级管理员90管理员",name="type",example="0")
    private Integer type;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间",name="createTime",example="1")
    private Date createTime;

    /**
     * 更新时间
     */@ApiModelProperty(value="更新时间",name="updateTime",example="1")
    private Date updateTime;

    /**
     * 角色ID
     */
    @ApiModelProperty(value="角色ID",name="roleIds",example="1")
    private List<String> roleIds;

    /**
     * 机构ID
     */
    @ApiModelProperty(value="机构ID",name="organIds",example="1")
    private List<String> organIds;

}
