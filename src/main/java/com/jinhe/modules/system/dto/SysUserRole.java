package com.jinhe.modules.system.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author rls
 * @since 2020-04-16
 */
@Data
@ApiModel(value="角色",description="角色")
public class SysUserRole {

    /**
     * 主键ID
     */
    @ApiModelProperty(value="主键ID",name="id",example="1")
    private String id;

    /**
     * 用户id
     */
    @ApiModelProperty(value="用户id",name="userId",example="1")
    private String userId;

    /**
     * 角色id
     */
    @ApiModelProperty(value="角色id",name="roleId",example="1")
    private String roleId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间",name="createTime",example="1")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value="更新时间",name="updateTime",example="1")
    private LocalDateTime updateTime;

}
