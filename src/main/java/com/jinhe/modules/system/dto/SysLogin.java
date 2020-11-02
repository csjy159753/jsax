package com.jinhe.modules.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Administrator
 */
@Data
@ApiModel(value="Login对象",description="登录")
public class SysLogin {
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


}
