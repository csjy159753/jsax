package com.jinhe.modules.system.service;

import com.jinhe.common.util.ListSub;
import com.jinhe.modules.system.dto.SysResourceDTO;
import com.jinhe.modules.system.entity.SysResource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 资源菜单 服务类
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
public interface ISysResourceService extends IService<SysResource> {
    /**
     * 查询所有菜单只有超级管理员和管路员有此权限
     *
     * @return
     */
    List<SysResourceDTO> selectPageAll();

    /**
     * 根据用户id获取用户菜单
     *
     * @param userId
     * @return
     */
    List<SysResourceDTO> listResource(String userId);
}
