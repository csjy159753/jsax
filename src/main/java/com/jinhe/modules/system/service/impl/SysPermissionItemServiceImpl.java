package com.jinhe.modules.system.service.impl;

import com.jinhe.modules.system.dto.SysResourceDTO;
import com.jinhe.modules.system.entity.SysPermissionItem;
import com.jinhe.modules.system.dao.SysPermissionItemMapper;
import com.jinhe.modules.system.service.ISysPermissionItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色授权子项对应页面按钮操作权限 服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
@Service
public class SysPermissionItemServiceImpl extends ServiceImpl<SysPermissionItemMapper, SysPermissionItem> implements ISysPermissionItemService {
}
