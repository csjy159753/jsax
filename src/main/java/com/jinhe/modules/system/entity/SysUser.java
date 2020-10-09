package com.jinhe.modules.system.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author rls
 * @since 2020-03-12
 */
@Data
@ApiModel(value="用户",description="用户")
public class SysUser {

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
    private Date lockOutTime;

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
     *工作单位
     */
    private String workUnit;
    /**
     * 工作职务
     */
    private String  jobName;
    /**
     * 工作部门
     */
    private String departmentName;

    private  String note;
}
