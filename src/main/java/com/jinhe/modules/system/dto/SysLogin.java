package com.jinhe.modules.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

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
    @ApiModelProperty(value="密码",name="passWord",example="123")
    private String passWord;


}
