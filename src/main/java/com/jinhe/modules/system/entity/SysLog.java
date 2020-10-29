package com.jinhe.modules.system.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 'mashz.act_id_user' is not BASE TABLE
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysLog对象", description="'mashz.act_id_user' is not BASE TABLE")
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty(value = "程序登录类型 ")
    private String application;

    @ApiModelProperty(value = "等级")
    private String level;

    @ApiModelProperty(value = "日志标记")
    private String logger;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "服务名称")
    private String serverName;

    @ApiModelProperty(value = "访问url")
    private String url;

    @ApiModelProperty(value = "传输内容")
    private String content;

    @ApiModelProperty(value = "远程ip")
    private String remoteAddress;

    @ApiModelProperty(value = "访问站点")
    private String callSite;

    @ApiModelProperty(value = "消息")
    private String message;

    @ApiModelProperty(value = "错误信息")
    private String exception;

    @ApiModelProperty(value = "其他")
    private String other;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新")
    private LocalDateTime updateTime;


}
