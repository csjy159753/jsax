package com.jinhe.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.modules.system.dto.SysResourceItemDto;
import com.jinhe.modules.system.entity.SysResourceItem;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author rls
 * @since 2020-05-19
 */
public interface ISysResourceItemService extends IService<SysResourceItem> {

    IPage<SysResourceItemDto> getListById(Page page, String id);

    SysResourceItem add(SysResourceItem sysRes);

    Integer modify(SysResourceItem sysRes, String id);
}
