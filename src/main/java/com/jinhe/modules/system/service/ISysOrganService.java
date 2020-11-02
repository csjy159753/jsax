package com.jinhe.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jinhe.modules.system.dto.SysOrganDTO;
import com.jinhe.modules.system.entity.SysOrgan;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 组织机构 服务类
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
public interface ISysOrganService extends IService<SysOrgan> {

    /**
     * 根据用户id查询机构子项
     *
     * @param organId
     * @return
     */
    List<SysOrganDTO> selectOrganByOrganId(String organId);
}
