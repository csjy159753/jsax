package com.jinhe.modules.system.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Administrator
 */
public class SysPasswordDTO implements Serializable {
    /**
     * 用户id
     */
    @ApiModelProperty(value="用户id",name="",example="123")
    private String userId;

    /**
     * 用户密码
     */
    @ApiModelProperty(value="用户密码",name="",example="123")
    private String passWordHash;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassWordHash() {
        return passWordHash;
    }

    public void setPassWordHash(String passWordHash) {
        this.passWordHash = passWordHash;
    }
}
