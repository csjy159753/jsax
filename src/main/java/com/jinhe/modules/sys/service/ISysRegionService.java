package com.jinhe.modules.sys.service;

import com.jinhe.common.config.ResultEnum;
import com.jinhe.config.SystemResultEnum;
import com.jinhe.modules.system.entity.SysRegion;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 行政区编码 服务类
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
public interface ISysRegionService extends IService<SysRegion> {
    /**
     * 根据id更新对象子项数量
     *
     * @param id
     * @return
     */
    Integer getChildrenNum(String id);

    /**
     * 根据id更新对象子项数量和层级树
     *
     * @param code
     * @return
     */
    ResultEnum saveOrUpdateChildrenNumAndLevel(String code);

    /**
     * 根据对象更新对象子项数量和层级树
     *
     * @param dictionary
     * @return
     */
    ResultEnum saveOrUpdateChildrenNumAndLevel(SysRegion dictionary);
}
