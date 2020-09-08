package com.jinhe.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @since 2020-05-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysPermission对象", description="")
public class SysPermission implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键ID")
    @TableId("ID")
    private String id;

    @ApiModelProperty(value = "父ID")
    @TableField("PARENT_ID")
    private String parentId;

    @ApiModelProperty(value = "创建者")
    @TableField("CREATE_USER_ID")
    private String createUserId;

    @ApiModelProperty(value = "状态0正常1警用")
    @TableField("STATE")
    private Integer state;

    @ApiModelProperty(value = "机构id")
    @TableField("ORGION_ID")
    private String orgionId;

    @ApiModelProperty(value = "角色id")
    @TableField("ROLE_ID")
    private String roleId;

    @ApiModelProperty(value = "用户id")
    @TableField("USER_ID")
    private String userId;

    @ApiModelProperty(value = "资源id")
    @TableField("RESOURCE_ID")
    private String resourceId;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//接收参数
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("UPDATE_TIME")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//接收参数
    private Date updateTime;

    @ApiModelProperty(value = "子权限id")
    @TableField("ITEM_IDS")
    private String itemIds;

    @ApiModelProperty(value = "类型0web1手机")
    @TableField("TYPE")
    private Integer type;


}
