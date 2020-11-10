package com.jinhe.modules.system.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author rls
 * @since 2020-11-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysOperatorLog对象", description="")
public class SysOperatorLog implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "模块名称")
    private String moduleName;

    @ApiModelProperty(value = "模块类型")
    private String moduleType;

    @ApiModelProperty(value = "操作描述")
    private String operator;

    @ApiModelProperty(value = "操作员id")
    private String operatorUserId;

    @ApiModelProperty(value = "操作员名称")
    private String operatorUserName;

    @ApiModelProperty(value = "操作方法")
    private String operatorMethod;

    @ApiModelProperty(value = "请求参数")
    private String operatoraArgs;

    @ApiModelProperty(value = "操作url")
    private String operatorUrl;

    @ApiModelProperty(value = "请求ip")
    private String operatorIp;

    @ApiModelProperty(value = "版本号")
    private String systemVersion;

    @ApiModelProperty(value = "操作时间")
    private LocalDateTime operatorTime;


}
