package com.jinhe.modules.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Data
@ApiModel(value="用户",description="用户")
public class SysUserNew extends SysUser {

    /**
     * 用户id
     */
    private String id;

    /**
     * 登录名
     */
    private String normalizedUsername;

    /**
     * 密码
     */
    private String passwordHash;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像地址
     */
    private String avatarUrl;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * openid
     */
    private String openId;

    /**
     * 性别0未知1男2女
     */
    private Integer sex;

    /**
     * 0正常1待审核2禁用3逻辑删除
     */
    private Integer state;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 电子邮件
     */
    private String normalizedEmail;

    /**
     * 电话号码是否验证0默认未认证1已认证
     */

    private Integer phoneNumberConfirmed;

    /**
     * 电子邮箱是否验证0默认未认证1已认证
     */
    private Integer emailConfirmed;

    /**
     * 锁定时间
     */
    private Timestamp lockOutTime;

    /**
     * 是否启用锁定0不启用1启用
     */
    private Integer lockOutEnabled;

    /**
     * 访问失败统计
     */
    private Integer accessFailedCount;

    /**
     * 角色类型0普通99超级管理员90管理员
     */
    private Integer type;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//接收参数
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 角色ID
     */
    private List<String> roleIds;

    /**
     * 机构ID
     */
    private List<String> organIds;


//    /**
//     * 角色集合
//     */
//    @ApiModelProperty(value="角色列表",name="roles",example="[string]")
//    private List<SysRoleNew> roles;
//
//
//
//    /**
//     * 机构集合
//     */
//    @ApiModelProperty(value="机构列表",name="organs",example="[string]")
//    private List<SysOrganNew> organs;

}
