package com.jinhe.modules.system.entity;

import java.util.Date;

import java.io.Serializable;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author rls
 * @since 2020-03-12
 */
@Data
@Accessors(chain = true)
@TableName("sys_user")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId("ID")
    private String id;

    /**
     * 登录名
     */
    @TableField("NORMALIZED_USERNAME")
    private String normalizedUsername;

    /**
     * 密码
     */
    @TableField("PASSWORD_HASH")
    private String passwordHash;

    /**
     * 昵称
     */
    @TableField("NICK_NAME")
    private String nickName;

    /**
     * 头像地址
     */
    @TableField("AVATAR_URL")
    private String avatarUrl;

    /**
     * 真实姓名
     */
    @TableField("REAL_NAME")
    private String realName;

    /**
     * openid
     */
    @TableField("OPEN_ID")
    private String openId;

    /**
     * 性别0未知1男2女
     */
    @TableField("SEX")
    private Integer sex;

    /**
     * 0正常1待审核2禁用3逻辑删除
     */
    @TableField("STATE")
    private Integer state;

    /**
     * 手机号码
     */
    @TableField("PHONE_NUMBER")
    private String phoneNumber;

    /**
     * 电子邮件
     */
    @TableField("NORMALIZED_EMAIL")
    private String normalizedEmail;

    /**
     * 电话号码是否验证0默认未认证1已认证
     */
    @TableField("PHONE_NUMBER_CONFIRMED")
    private Integer phoneNumberConfirmed;

    /**
     * 电子邮箱是否验证0默认未认证1已认证
     */
    @TableField("EMAIL_CONFIRMED")
    private Integer emailConfirmed;

    /**
     * 锁定时间
     */
    @TableField("LOCK_OUT_TIME")
    private Date lockOutTime;

    /**
     * 是否启用锁定0不启用1启用
     */
    @TableField("LOCK_OUT_ENABLED")
    private Integer lockOutEnabled;

    /**
     * 访问失败统计
     */
    @TableField("ACCESS_FAILED_COUNT")
    private Integer accessFailedCount;

    /**
     * 角色类型0普通99超级管理员90管理员
     */
    @TableField("TYPE")
    private Integer type;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
