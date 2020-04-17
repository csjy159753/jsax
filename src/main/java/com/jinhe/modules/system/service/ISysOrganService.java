package com.jinhe.modules.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.modules.system.dto.SysOrganDto;
import com.jinhe.modules.system.entity.SysOrgan;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jinhe.modules.system.entity.SysResource;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rls
 * @since 2020-04-16
 */
public interface ISysOrganService extends IService<SysOrgan> {
     Page<SysOrgan> selectPageOrgan(Page page);

     SysOrganDto selectPageOrganByID(String ID);
}
