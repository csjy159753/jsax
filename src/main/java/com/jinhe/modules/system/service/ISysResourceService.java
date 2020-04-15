package com.jinhe.modules.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.jinhe.modules.system.dto.SysResourceDto;
import com.jinhe.modules.system.entity.SysResource;
import io.swagger.models.auth.In;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rls
 * @since 2020-04-13
 */
public interface ISysResourceService extends IService<SysResource> {

  Page<SysResource> selectSysResourcepage(Page page);

  SysResourceDto Select_SysRespagebyid(String ID);

  Integer sysresourcesave(SysResourceDto sysresdto);
}
