package com.jinhe.modules.sys.dto;

import com.jinhe.modules.system.entity.SysOrgan;
import com.jinhe.modules.system.entity.SysUser;

import java.util.List;

/**
 * @author Administrator
 */
public class UserInfoDTO {
    private SysUser sysUser;
    private List<SysOrgan> listOrgan;

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public List<SysOrgan> getListOrgan() {
        return listOrgan;
    }

    public void setListOrgan(List<SysOrgan> listOrgan) {
        this.listOrgan = listOrgan;
    }
}
