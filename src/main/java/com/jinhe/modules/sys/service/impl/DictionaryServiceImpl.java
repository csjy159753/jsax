package com.jinhe.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jinhe.common.util.EntityUtil;
import com.jinhe.common.util.StringUtils;
import com.jinhe.config.LongSwingConstants;
import com.jinhe.config.ResultEnum;
import com.jinhe.modules.sys.entity.Dictionary;
import com.jinhe.modules.sys.dao.DictionaryMapper;
import com.jinhe.modules.sys.service.IDictionaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-11-19
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements IDictionaryService {

    @Override
    public Integer getChildrenNum(String id) {
        QueryWrapper<Dictionary> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(Dictionary::getParentId, id);
        return this.getBaseMapper().selectCount(queryWrapper);
    }

    @Override
    public ResultEnum saveOrUpdateChildrenNumAndLevel(String id) {
        Dictionary dictionary = this.getById(id);
        if (dictionary == null) {
            return ResultEnum.NOT_FOUND;
        }
        //#更新数量
        QueryWrapper<Dictionary> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(Dictionary::getParentId, id);
        Integer count = this.getBaseMapper().selectCount(queryWrapper);
        dictionary.setChildrenNum(count);

        //#更新层级
        if (StringUtils.isEmpty(dictionary.getParentId())) {
            dictionary.setLevelInfo(LongSwingConstants.Number.ONE);
        } else {
            QueryWrapper<Dictionary> queryWrapperLevel = new QueryWrapper();
            queryWrapperLevel.lambda().eq(Dictionary::getId, dictionary.getParentId());
            Integer Level = this.getBaseMapper().selectCount(queryWrapperLevel);
            dictionary.setLevelInfo(Level + LongSwingConstants.Number.ONE);
        }
        this.saveOrUpdate(dictionary);
        return ResultEnum.SUCCESS;
    }

    @Override
    public ResultEnum saveOrUpdateChildrenNumAndLevel(Dictionary from) {
        Dictionary dictionary = this.getById(from.getId());
        if (from == null) {
            return ResultEnum.NOT_FOUND;
        }
        dictionary = EntityUtil.INSTANCE.copyValOnlyDestEmpty(dictionary, from);
        QueryWrapper<Dictionary> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(Dictionary::getParentId, dictionary.getId());
        Integer count = this.getBaseMapper().selectCount(queryWrapper);
        dictionary.setChildrenNum(count);
        this.saveOrUpdate(dictionary);
        return ResultEnum.SUCCESS;
    }
}
