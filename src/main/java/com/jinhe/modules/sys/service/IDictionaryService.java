package com.jinhe.modules.sys.service;

import com.jinhe.common.config.ResultEnum;
import com.jinhe.config.SystemResultEnum;
import com.jinhe.modules.system.entity.Dictionary;
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
    public static String[] excludeFields = new String[]{"levelInfo", "createTime", "updateTime", "isSystem", "status", "remark", "childrenNum"};

    /**
     * 保存字典
     *
     * @param dictionary
     * @return
     */
    ResultEnum saveDictionary(Dictionary dictionary);

    /**
     * 更新字典
     *
     * @param dictionary
     * @return
     */
    ResultEnum updateDictionary(Dictionary dictionary);

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


}
