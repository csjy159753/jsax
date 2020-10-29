package com.jinhe.modules.system.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class SysLoginDto implements Serializable {
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

}
