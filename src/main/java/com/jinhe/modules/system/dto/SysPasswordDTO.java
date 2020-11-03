package com.jinhe.modules.system.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
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


}
