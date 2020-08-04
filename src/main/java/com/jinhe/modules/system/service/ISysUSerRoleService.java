package com.jinhe.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jinhe.modules.system.entity.SysUserRole;

import java.util.List;

public interface ISysUSerRoleService extends IService<SysUserRole> {

    //新增
    boolean insertUserRole(List<SysUserRole> list);

    //根据userId删除
    int deleteByUserId(String userId);
}
