package com.jinhe.modules.system.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author rls
 * @since 2020-04-15
 */
@Data
public class SysLogVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */

    private String id;

    /**
     * 程序登录类型
     */

    private String application;

    /**
     * 等级
     */

    private String level;


    private String logger;


    private String userName;


    private String serverName;


    private String url;


    private String remoteAddress;


    private String callSite;


    private String message;


    private String other;
    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//接收参数
    @JsonSerialize(using = LocalDateTimeSerializer.class)//显示
    private Date createTime;
    /**
     * 更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//接收参数
    @JsonSerialize(using = LocalDateTimeSerializer.class)//显示
    private Date updateTime;

    private String exception;


}
