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
    @ApiModelProperty(value="用户ID",name="id",example="123")
    private String id;

    /**
     * 父级id
     */
    private String parentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 标签
     */
    private String tag;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否锁定
     */
    private Integer lockOutEnabled;

    /**
     * 锁定时间
     */
    private Date lockOutTime;

    /**
     * 创建人id
     */
    private String operatorId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 更新时间
     */
    private Date updateTime;



}
