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
 * @since 2020-04-09
 */
@Data
@Accessors(chain = true)
@TableName("sys_log")
public class SysLog extends Model<SysLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId("ID")
    private String id;

    /**
     * 程序登录类型
     */
    @TableField("APPLICATION")
    private String application;

    /**
     * 等级
     */
    @TableField("LEVEL")
    private String level;

    @TableField("LOGGER")
    private String logger;

    @TableField("USER_NAME")
    private String userName;

    @TableField("SERVER_NAME")
    private String serverName;

    @TableField("URL")
    private String url;

    @TableField("REMOTE_ADDRESS")
    private String remoteAddress;

    @TableField("CALL_SITE")
    private String callSite;

    @TableField("MESSAGE")
    private String message;

    @TableField("OTHER")
    private String other;

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

    @TableField("EXCEPTION")
    private String exception;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
