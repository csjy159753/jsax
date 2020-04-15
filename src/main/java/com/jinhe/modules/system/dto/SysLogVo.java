package com.jinhe.modules.system.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.io.Serializable;
import java.time.LocalDateTime;

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




    private String exception;


}
