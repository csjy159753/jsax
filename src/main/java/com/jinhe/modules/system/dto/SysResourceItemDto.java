package com.jinhe.modules.system.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jinhe.modules.system.entity.SysResource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

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
@TableName("SYS_RESOURCE_ITEM")
@ApiModel(value="SysResourceItem对象", description="")
public class SysResourceItemDto implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键id")
    @TableId("ID")
    private String id;

    @ApiModelProperty(value = "资源id")
    @TableField("RESOURCE_ID")
    private String resourceId;

    @ApiModelProperty(value = "显示名称")
    @TableField("DISPLAY_NAME")
    private String displayName;

    @ApiModelProperty(value = "状态：0正常 1禁用")
    @TableField("STATE")
    private Integer state;

    @ApiModelProperty(value = "描述")
    @TableField("NOTE")
    private String note;

    @ApiModelProperty(value = "类型（预留）")
    @TableField("TYPE")
    private Integer type;

    @ApiModelProperty(value = "排序")
    @TableField("SORT")
    private Integer sort;

    @ApiModelProperty(value = "英文名")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//接收参数
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("UPDATE_TIME")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//接收参数
    private Date updateTime;

    private SysResource resource;


}
