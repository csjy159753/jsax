package com.jinhe.modules.sys.service;

import com.jinhe.config.ResultEnum;
import com.jinhe.modules.sys.dto.SysOrganDTO;
import com.jinhe.modules.system.entity.Dictionary;
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
     * 根据id更新对象子项数量
     *
     * @param id
     * @return
     */
    Integer getChildrenNum(String id);

    /**
     * 根据id更新对象子项数量和层级树
     *
     * @param id
     * @return
     */
    ResultEnum saveOrUpdateChildrenNumAndLevel(String id);

    /**
     * 根据对象更新对象子项数量和层级树
     *
     * @param dictionary
     * @return
     */
    ResultEnum saveOrUpdateChildrenNumAndLevel(SysOrgan dictionary);
}
