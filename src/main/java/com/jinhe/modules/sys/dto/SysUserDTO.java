package com.jinhe.modules.sys.dto;

import com.jinhe.modules.system.entity.SysUser;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author Administrator
 */
public class SysUserDTO extends SysUser {
    @ApiModelProperty(value = "id,value", name = "organId", example = "机构名称")
    private String organs;

    public String getOrgans() {
        return organs;
    }

    public void setOrgans(String organs) {
        this.organs = organs;
    }

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
