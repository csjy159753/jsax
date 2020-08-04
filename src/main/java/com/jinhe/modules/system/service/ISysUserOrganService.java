package com.jinhe.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jinhe.modules.system.entity.SysUserOrgan;

import java.util.List;

public interface ISysUserOrganService extends IService<SysUserOrgan> {

    //新增
    boolean insertUserOrgan(List<SysUserOrgan> list);

    boolean insert(SysUserOrgan organ);

    //删除
    boolean delete(String id);

    //根据ID查找
    SysUserOrgan selectById(String id);

    //根据userId删除
    int deleteByUserId(String userId);
}
