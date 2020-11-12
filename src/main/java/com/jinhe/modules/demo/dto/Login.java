package com.jinhe.modules.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value="Login对象",description="登录")
public class Login {

    /**
     * 用户名
     */
    @ApiModelProperty(value="用户名",name="userName",example="superAdmin")
    private String userName;
    /**
     * 密码
     */
    @ApiModelProperty(value="密码",name="passWord",example="MmViNjNlMmQ2MmZjMGU2NGRiNWIxMjA4ODViODI2MjE=")
    private String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
