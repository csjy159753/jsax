package com.jinhe.modules.system.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SysUserDto  {
    /**
     * 用户id
     */
    private String id;

    /**
     * 登录名
     */
    private String normalizedUserName;

    /**
     * 用户密码
     */
    private String passwordHash;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 所属机构
     */
    private String organName;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 电子邮件
     */
    private String normalizedEmail;

    /**
     * 0正常1待审核2禁用3逻辑删除
     */
    private Integer state;

    /**
     * 角色ID
     */
    private List<String> roleIds;

    /**
     * 机构ID
     */
    private List<String> organIds;

}
