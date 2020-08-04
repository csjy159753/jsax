package com.jinhe.modules.system.dto;

import com.jinhe.modules.system.entity.SysOrgan;
import com.jinhe.modules.system.entity.SysRole;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SysUserRoleOrganDto implements Serializable {
    private SysUserDto sysUser;

    private List<SysOrgan> organ;

    private List<SysRole> role;
}
