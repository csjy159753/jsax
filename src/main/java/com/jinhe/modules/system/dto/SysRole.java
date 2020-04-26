package com.jinhe.modules.system.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author rls
 * @since 2020-04-14
 */
@Data
@ApiModel(value="角色",description="角色")
public class SysRole {
    /**
     * 主键id
     */
    @ApiModelProperty(value="ID",name="id",example="123")
    private String id;

    /**
     * 父级id
     */
    @ApiModelProperty(value="父级ID",name="parentId",example="123")
    private String parentId;

    /**
     * 名称
     */
    @ApiModelProperty(value="名称",name="name",example="admin")
    private String name;

    /**
     * 标签
     */
    @ApiModelProperty(value="标签",name="tag",example="管理员")
    private String tag;

    /**
     * 类型
     */
    @ApiModelProperty(value="类型",name="type",example="1")
    private Integer type;

    /**
     * 排序
     */
    @ApiModelProperty(value="排序",name="sort",example="1")
    private Integer sort;

    /**
     * 是否锁定
     */
    @ApiModelProperty(value="是否锁定",name="lockOutEnabled",example="1")
    private Integer lockOutEnabled;

    /**
     * 锁定时间
     */
    @ApiModelProperty(value="锁定时间",name="lockOutTime",example="1")
    private Date lockOutTime;

    /**
     * 创建人id
     */
    @ApiModelProperty(value="创建人id",name="operatorId",example="1")
    private String operatorId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间",name="createTime",example="1")
    private Date createTime;

    /**
     * 描述
     */
    @ApiModelProperty(value="描述",name="description",example="1")
    private String description;

    /**
     * 状态
     */
    @ApiModelProperty(value="状态",name="state",example="1")
    private Integer state;

    /**
     * 更新时间
     */
    @ApiModelProperty(value="更新时间",name="updateTime",example="1")
    private Date updateTime;



}
