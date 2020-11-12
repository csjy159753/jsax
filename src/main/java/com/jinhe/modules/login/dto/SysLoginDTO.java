package com.jinhe.modules.login.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Administrator
 */
public class SysLoginDTO implements Serializable {
    /**
     * 用户id
     */
    @ApiModelProperty(value="用户id",name="",example="123")
    private String id;

    /**
     * 0正常1待审核2禁用3逻辑删除
     */
    @ApiModelProperty(value="状态 0正常1待审核2禁用3逻辑删除",name="state",example="0")
    private Integer state;
    /**
     * 机构名称
     */
    @ApiModelProperty(value="令牌",name="token",example="1")
    private String token;

    /**
     * 机构名称
     */
    @ApiModelProperty(value="失效时间",name="tokenExpireTime",example="1")
    private long tokenExpireTime;

    /**
     * 机构名称
     */
    @ApiModelProperty(value="加密令牌",name="refreshToken",example="1")
    private String refreshToken;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getTokenExpireTime() {
        return tokenExpireTime;
    }

    public void setTokenExpireTime(long tokenExpireTime) {
        this.tokenExpireTime = tokenExpireTime;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
