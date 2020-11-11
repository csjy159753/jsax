package com.jinhe.modules.system.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 角色授权
 * </p>
 *
 * @author rls
 * @since 2020-11-11
 */
@ApiModel(value="SysAppPermission对象", description="角色授权")
class SysAppPermission : Serializable {

    @ApiModelProperty(value = "主键id")
    var id: String? = null
    @ApiModelProperty(value = "父id")
    var parentId: String? = null
    @ApiModelProperty(value = "创建者")
    var createUserId: String? = null
    @ApiModelProperty(value = "状态0正常1禁用")
    var state: Int? = null
    @ApiModelProperty(value = "角色id")
    var roleId: String? = null
    @ApiModelProperty(value = "资源id")
    var resourceId: String? = null
    @ApiModelProperty(value = "创建时间")
    var createTime: LocalDateTime? = null
    @ApiModelProperty(value = "更新")
    var updateTime: LocalDateTime? = null


    override fun toString(): String {
        return "SysAppPermission{" +
        "id=" + id +
        ", parentId=" + parentId +
        ", createUserId=" + createUserId +
        ", state=" + state +
        ", roleId=" + roleId +
        ", resourceId=" + resourceId +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}"
    }
}
