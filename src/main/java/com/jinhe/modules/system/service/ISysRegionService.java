package com.jinhe.modules.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.jinhe.modules.system.dto.SysRegionDTO;
import com.jinhe.modules.system.entity.SysRegion;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author rls
 * @since 2020-04-16
 */
public interface ISysRegionService extends IService<SysRegion> {

    //查询行政区列表
    List<SysRegionDTO> selectRegionList(String code);

    //新增行政区
    Boolean addRegion(SysRegion sysRegion);

    //更新行政区
    Integer updateRegion(SysRegion sysRegion, String id);

    //删除行政区
    Integer delRegion(String id);

    SysRegion regionForCode(String code);
}
