package com.jinhe.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 'mashz.act_id_user' is not BASE TABLE
 * </p>
 *
 * @author rls
 * @since 2020-10-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysLog对象", description="'mashz.act_id_user' is not BASE TABLE")
public class SysLog implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;

    @ApiModelProperty(value = "程序登录类型 ")
    private String application;

    @ApiModelProperty(value = "等级")
    @TableField("level_Info")
    private String levelInfo;

    private String logger;

    private String userName;

    private String serverName;

    private String url;

    private String remoteAddress;

    private String callSite;

    private String message;

    private String exception;

    private String other;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新")
    private LocalDateTime updateTime;


}
