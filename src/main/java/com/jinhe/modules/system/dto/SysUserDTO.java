package com.jinhe.modules.system.dto;

import com.jinhe.modules.system.entity.SysUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Administrator
 */
@Data
public class SysUserDTO extends SysUser {
    /**
     * 机构集合
     */
    @ApiModelProperty(value = "机构id列表", name = "organId", example = "[string]")
    private List<String> organIds;
}
