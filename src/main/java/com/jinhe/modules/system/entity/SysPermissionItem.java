package com.jinhe.modules.system.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色授权子项对应页面按钮操作权限
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysPermissionItem对象", description="角色授权子项对应页面按钮操作权限")
public class SysPermissionItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "授权permission_id")
    private String permissionId;

    @ApiModelProperty(value = "资源菜单id")
    private String resourceItemId;


}
