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
@ApiModel(value="SysLoginCount对象", description="'mashz.act_id_user' is not BASE TABLE")
public class SysLoginCount implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "总累计登录次数")
    private Integer count;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "登录名")
    private String userName;

    @ApiModelProperty(value = "登录成功记录的用户id")
    private String userId;

    @ApiModelProperty(value = "pc登录次数")
    private Integer pcCount;

    @ApiModelProperty(value = "app登录次数")
    private Integer appCount;


}
