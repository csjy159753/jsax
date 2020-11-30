package com.jinhe.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jinhe.common.util.StringUtils;
import com.jinhe.config.LongSwingConstants;
import com.jinhe.config.ResultEnum;
import com.jinhe.modules.system.entity.Dictionary;
import com.jinhe.modules.system.entity.SysRegion;
import com.jinhe.modules.sys.dao.SysRegionMapper;
import com.jinhe.modules.sys.service.ISysRegionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 行政区编码 服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
@Service
public class SysRegionServiceImpl extends ServiceImpl<SysRegionMapper, SysRegion> implements ISysRegionService {

    @Override
    public Integer getChildrenNum(String parentCode) {
        QueryWrapper<SysRegion> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(SysRegion::getParentCode, parentCode);
        return this.getBaseMapper().selectCount(queryWrapper);
    }

    @Override
    public ResultEnum saveOrUpdateChildrenNumAndLevel(String id) {

        SysRegion model = this.getById(id);
        if (model == null) {
            return ResultEnum.NOT_FOUND;
        }
        //#更新数量
        QueryWrapper<SysRegion> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(SysRegion::getParentCode, model.getParentCode());
        Integer count = this.getBaseMapper().selectCount(queryWrapper);
        model.setChildrenNum(count);

        //#更新层级
        if (StringUtils.isEmpty(model.getParentCode())) {
            model.setLevelInfo(LongSwingConstants.Number.ONE);
        } else {
            QueryWrapper<SysRegion> queryWrapperLevel = new QueryWrapper();
            queryWrapperLevel.lambda().eq(SysRegion::getCode, model.getParentCode());
            Integer Level = this.getBaseMapper().selectCount(queryWrapperLevel);
            model.setLevelInfo(Level + LongSwingConstants.Number.ONE);
        }
        this.saveOrUpdate(model);
        return ResultEnum.SUCCESS;
    }

    @Override
    public ResultEnum saveOrUpdateChildrenNumAndLevel(SysRegion from) {
        SysRegion model = this.getById(from.getId());
        if (model == null) {
            return ResultEnum.NOT_FOUND;
        }
        //#更新数量
        QueryWrapper<SysRegion> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(SysRegion::getParentCode, model.getId());
        Integer count = this.getBaseMapper().selectCount(queryWrapper);
        model.setChildrenNum(count);

        //#更新层级
        if (StringUtils.isEmpty(model.getParentCode())) {
            model.setLevelInfo(LongSwingConstants.Number.ONE);
        } else {
            QueryWrapper<SysRegion> queryWrapperLevel = new QueryWrapper();
            queryWrapperLevel.lambda().eq(SysRegion::getCode, model.getParentCode());
            Integer Level = this.getBaseMapper().selectCount(queryWrapperLevel);
            model.setLevelInfo(Level + LongSwingConstants.Number.ONE);
        }
        this.saveOrUpdate(model);
        return ResultEnum.SUCCESS;
    }
}
