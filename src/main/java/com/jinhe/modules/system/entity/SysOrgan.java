package com.jinhe.modules.system.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@ApiModel(value="SysOrgan对象", description="'mashz.act_id_user' is not BASE TABLE")
public class SysOrgan implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;

    @ApiModelProperty(value = "父id")
    private String parentId;

    @ApiModelProperty(value = "类型0代码部门或者行政区划1代码具体职务")
    private Integer type;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "类型对一个以后具体业务时候的字典表使用")
    private String tag;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "全名")
    private String fullName;

    @ApiModelProperty(value = "行政区编码")
    private String regionCode;

    @ApiModelProperty(value = "行政区名称")
    private String regionName;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "状态0正常1禁用")
    private Integer state;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "径路id集合保留字段暂时不用")
    private String path;

    @ApiModelProperty(value = "深度")
    private Integer depth;

    @ApiModelProperty(value = "创建人")
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新")
    private LocalDateTime updateTime;


}
