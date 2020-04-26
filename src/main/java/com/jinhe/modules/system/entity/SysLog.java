package com.jinhe.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author rls
 * @since 2020-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysLog implements Serializable {

    private static final long serialVersionUID=1L;

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

//    /**
//     * 等级
//     */
//    @TableField("LEVEL")
//    private String level;

    @TableField("LOGGER")
    private String logger;

    @TableField("USER_NAME")
    private String userName;

    @TableField("SERVER_NAME")
    private String serverName;

//    @TableField("URL")
//    private String url;

    @TableField("REMOTE_ADDRESS")
    private String remoteAddress;

    @TableField("CALL_SITE")
    private String callSite;

    @TableField("MESSAGE")
    private String message;

    @TableField("OTHER")
    private String other;

//    /**
//     * 创建时间
//     */
//    @TableField("CREATE_TIME")
//    private LocalDateTime createTime;
//
//    /**
//     * 更新时间
//     */
//    @TableField("UPDATE_TIME")
//    private LocalDateTime updateTime;

    @TableField("EXCEPTION")
    private String exception;


}
