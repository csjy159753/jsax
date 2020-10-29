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
@ApiModel(value="SysResourceItem对象", description="'mashz.act_id_user' is not BASE TABLE")
public class SysResourceItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    private String id;

    @ApiModelProperty(value = "资源id")
    private String resourceId;

    @ApiModelProperty(value = "英文名称")
    private String name;

    @ApiModelProperty(value = "显示名称")
    private String displayName;

    @ApiModelProperty(value = "描述")
    private String note;

    @ApiModelProperty(value = "状态0正常1禁用")
    private Integer state;

    @ApiModelProperty(value = "类型（预留）")
    private Integer type;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新")
    private LocalDateTime updateTime;


}
