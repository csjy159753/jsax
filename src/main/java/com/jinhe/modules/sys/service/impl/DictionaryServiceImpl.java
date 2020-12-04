package com.jinhe.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jinhe.common.util.EntityUtil;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.common.util.StringUtils;
import com.jinhe.config.LongSwingConstants;
import com.jinhe.config.ResultEnum;
import com.jinhe.modules.system.entity.Dictionary;
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
    public ResultEnum saveDictionary(Dictionary dictionary) {
        Dictionary dict = EntityUtil.INSTANCE.copyValOnlyDestNull(new Dictionary(), dictionary, excludeFields);
        if (dict.getParentId() != null) {
            {
                Dictionary DictionaryParent = this.getById(dict.getParentId());
                dict.setType(DictionaryParent.getType());
                QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().eq(Dictionary::getParentId, dict.getParentId()).eq(Dictionary::getValue, dict.getValue());
                int count = this.count(queryWrapper);
                if (count > 0) {
                    return ResultEnum.DICTIONARY_EXIST_SAME_VALUE;
                }
            }
            {
                QueryWrapper<Dictionary> queryWrapperA = new QueryWrapper<>();
                queryWrapperA.lambda().eq(Dictionary::getParentId, dict.getParentId()).eq(Dictionary::getName, dict.getName());
                int countA = this.count(queryWrapperA);
                if (countA > 0) {
                    return ResultEnum.DICTIONARY_EXIST_SAME_NAME;
                }
            }
            this.saveOrUpdateChildrenNumAndLevel(dictionary.getParentId());
        } else {
            // 字典类型不能为空
            if (dict.getType() == null) {
                return ResultEnum.DICTIONARY_NOT_TYPE;
            }
            //字典type不能重复目前值顶级重复判断子级自动获取顶级type
            {
                QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().isNull(Dictionary::getParentId).eq(Dictionary::getType, dict.getType());
                int count = this.count(queryWrapper);
                if (count > 0) {
                    return ResultEnum.DICTIONARY_TYPE_NOT_REPETITION;
                }
            }
            {
                QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().isNull(Dictionary::getParentId).eq(Dictionary::getValue, dict.getValue());
                int count = this.count(queryWrapper);
                if (count > 0) {
                    return ResultEnum.DICTIONARY_EXIST_SAME_VALUE;
                }
            }
            {
                QueryWrapper<Dictionary> queryWrapperA = new QueryWrapper<>();
                queryWrapperA.lambda().isNull(Dictionary::getParentId).eq(Dictionary::getName, dict.getName());
                int countA = this.count(queryWrapperA);
                if (countA > 0) {
                    return ResultEnum.DICTIONARY_EXIST_SAME_NAME;
                }
            }
        }
        this.saveOrUpdate(dict);
        return ResultEnum.SUCCESS;
    }

    @Override
    public ResultEnum updateDictionary(Dictionary dictionary) {
        Dictionary dict = this.getById(dictionary.getId());
        if (dict == null) {
            return ResultEnum.NOT_FOUND;
        } else {
            dict = EntityUtil.INSTANCE.copyValOnlyDestNull(dict, dictionary, excludeFields);
        }
        if (dict.getParentId() != null) {
            {
                QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().eq(Dictionary::getParentId, dict.getParentId()).eq(Dictionary::getValue, dict.getValue()).notIn(Dictionary::getId, dict.getId());
                int count = this.count(queryWrapper);
                if (count > 0) {
                    return ResultEnum.DICTIONARY_EXIST_SAME_VALUE;
                }
            }
            {
                QueryWrapper<Dictionary> queryWrapperA = new QueryWrapper<>();
                queryWrapperA.lambda().eq(Dictionary::getParentId, dict.getParentId()).eq(Dictionary::getName, dict.getName()).notIn(Dictionary::getId, dict.getId());
                int countA = this.count(queryWrapperA);
                if (countA > 0) {
                    return ResultEnum.DICTIONARY_EXIST_SAME_NAME;
                }
            }
            this.saveOrUpdateChildrenNumAndLevel(dictionary.getParentId());
        } else {
            {
                QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().isNull(Dictionary::getParentId).eq(Dictionary::getValue, dict.getValue()).notIn(Dictionary::getId, dict.getId());
                int count = this.count(queryWrapper);
                if (count > 0) {
                    return ResultEnum.DICTIONARY_EXIST_SAME_VALUE;
                }
            }
            {
                QueryWrapper<Dictionary> queryWrapperA = new QueryWrapper<>();
                queryWrapperA.lambda().isNull(Dictionary::getParentId).eq(Dictionary::getName, dict.getName()).notIn(Dictionary::getId, dict.getId());
                int countA = this.count(queryWrapperA);
                if (countA > 0) {
                    return ResultEnum.DICTIONARY_EXIST_SAME_NAME;
                }
            }
        }
        this.saveOrUpdate(dict);

        return ResultEnum.SUCCESS;
    }

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
        queryWrapper.lambda().eq(Dictionary::getParentId, dictionary.getId());
        Integer count = this.getBaseMapper().selectCount(queryWrapper);
        dictionary.setChildrenNum(count);

        //#更新层级
        if (StringUtils.isEmpty(dictionary.getParentId())) {
            dictionary.setLevelInfo(LongSwingConstants.Number.ONE);
        } else {
            Dictionary dict = this.getBaseMapper().selectById(dictionary.getParentId());
            dictionary.setLevelInfo(dict.getLevelInfo() + LongSwingConstants.Number.ONE);
        }
        this.saveOrUpdate(dictionary);
        return ResultEnum.SUCCESS;
    }
}
