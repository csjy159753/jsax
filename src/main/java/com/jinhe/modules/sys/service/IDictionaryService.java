package com.jinhe.modules.sys.service;

import com.jinhe.config.ResultEnum;
import com.jinhe.modules.sys.entity.Dictionary;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author rls
 * @since 2020-11-19
 */
public interface IDictionaryService extends IService<Dictionary> {
    /**
     * 根据id更新对象子项数量
     *
     * @param id
     * @return
     */
    Integer getChildrenNum(String id);

    /**
     *  根据id更新对象子项数量和层级树
     * @param id
     * @return
     */
    ResultEnum saveOrUpdateChildrenNumAndLevel(String id);

    /**
     * 根据对象更新对象子项数量和层级树
     * @param dictionary
     * @return
     */
    ResultEnum saveOrUpdateChildrenNumAndLevel(Dictionary dictionary);
}
