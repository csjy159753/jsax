package com.jinhe.modules.system.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author rls
 * @since 2020-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysRole对象", description="")
public class SysRole implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;

    @ApiModelProperty(value = "父级ID")
    private String parentId;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "标签")
    private String tag;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "状态")
    private Integer state;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "是否锁定")
    private Integer lockOutEnabled;

    @ApiModelProperty(value = "锁定时间")
    private LocalDateTime lockOutTime;

    @ApiModelProperty(value = "创建人id")
    private String operatorId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新")
    private LocalDateTime updateTime;


}
