package com.jinhe.modules.system.entity;

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import java.time.LocalDateTime
/**
 * <p>
 * 
 * </p>
 *
 * @author rls
 * @since 2020-11-11
 */
@ApiModel(value="SysAppResource对象", description="")
class SysAppResource : Serializable {

    var id: String? = null
    @ApiModelProperty(value = "父建id")
    var parentId: String? = null
    @ApiModelProperty(value = "英文名称")
    var name: String? = null
    @ApiModelProperty(value = "显示名称")
    var displayName: String? = null
    @ApiModelProperty(value = "描述")
    var note: String? = null
    @ApiModelProperty(value = "状态0正常1禁用")
    var state: Int? = null
    @ApiModelProperty(value = "类型（预留）")
    var type: Int? = null
    @ApiModelProperty(value = "排序")
    var sort: Int? = null
    @ApiModelProperty(value = "小图标")
    var icon: String? = null
    @ApiModelProperty(value = "创建者id")
    var createUserId: String? = null
    @ApiModelProperty(value = "创建时间")
    var createTime: LocalDateTime? = null
    @ApiModelProperty(value = "更新")
    var updateTime: LocalDateTime? = null


    override fun toString(): String {
        return "SysAppResourse{" +
        "id=" + id +
        ", parentId=" + parentId +
        ", name=" + name +
        ", displayName=" + displayName +
        ", note=" + note +
        ", state=" + state +
        ", type=" + type +
        ", sort=" + sort +
        ", icon=" + icon +
        ", createUserId=" + createUserId +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}"
    }
}
