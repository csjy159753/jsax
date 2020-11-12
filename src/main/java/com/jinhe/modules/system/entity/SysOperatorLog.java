package com.jinhe.modules.system.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 
 * </p>
 *
 * @author rls
 * @since 2020-11-09
 */
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperatorUserId() {
        return operatorUserId;
    }

    public void setOperatorUserId(String operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    public String getOperatorUserName() {
        return operatorUserName;
    }

    public void setOperatorUserName(String operatorUserName) {
        this.operatorUserName = operatorUserName;
    }

    public String getOperatorMethod() {
        return operatorMethod;
    }

    public void setOperatorMethod(String operatorMethod) {
        this.operatorMethod = operatorMethod;
    }

    public String getOperatoraArgs() {
        return operatoraArgs;
    }

    public void setOperatoraArgs(String operatoraArgs) {
        this.operatoraArgs = operatoraArgs;
    }

    public String getOperatorUrl() {
        return operatorUrl;
    }

    public void setOperatorUrl(String operatorUrl) {
        this.operatorUrl = operatorUrl;
    }

    public String getOperatorIp() {
        return operatorIp;
    }

    public void setOperatorIp(String operatorIp) {
        this.operatorIp = operatorIp;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public LocalDateTime getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(LocalDateTime operatorTime) {
        this.operatorTime = operatorTime;
    }
}
