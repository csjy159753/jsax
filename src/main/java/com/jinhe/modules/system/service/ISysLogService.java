package com.jinhe.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.common.util.Tree.TreeNode;
import com.jinhe.modules.system.dto.SysUserVo;
import com.jinhe.modules.system.entity.SysLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jinhe.modules.system.entity.SysRole;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rls
 * @since 2020-04-15
 */
public interface ISysLogService extends IService<SysLog> {
    IPage<SysLog> selectPageVo(Page<SysLog> page, SysLog sysLog);
    List<TreeNode> selectSysRoleVo(HashMap map);
}
