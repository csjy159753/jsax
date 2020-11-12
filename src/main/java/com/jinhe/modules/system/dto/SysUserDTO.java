package com.jinhe.modules.system.dto;

import com.jinhe.modules.system.entity.SysUser;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author Administrator
 */
public class SysUserDTO extends SysUser {
    /**
     * 机构集合
     */
    @ApiModelProperty(value = "机构id列表", name = "organId", example = "[string]")
    private List<String> organIds;

    public List<String> getOrganIds() {
        return organIds;
    }

    public void setOrganIds(List<String> organIds) {
        this.organIds = organIds;
    }
}
