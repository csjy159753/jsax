package com.jinhe.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jinhe.common.util.StringUtils;
import com.jinhe.common.config.LongSwingConstants;
import com.jinhe.config.ResultEnum;
import com.jinhe.modules.system.entity.SysOrgan;
import com.jinhe.modules.sys.dao.SysOrganMapper;
import com.jinhe.modules.sys.service.ISysOrganService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 组织机构 服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
@Service
public class SysOrganServiceImpl extends ServiceImpl<SysOrganMapper, SysOrgan> implements ISysOrganService {

    @Autowired
    private SysOrganMapper sysOrganMapper;


    @Override
    public Integer getChildrenNum(String id) {
        QueryWrapper<SysOrgan> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(SysOrgan::getParentId, id);
        return this.getBaseMapper().selectCount(queryWrapper);
    }

    @Override
    public ResultEnum saveOrUpdateChildrenNumAndLevel(String id) {
        SysOrgan model = this.getById(id);
        if (saveOrUpdateNumLevel(model)) {
            return ResultEnum.NOT_FOUND;
        }
        return ResultEnum.SUCCESS;
    }


    @Override
    public ResultEnum saveOrUpdateChildrenNumAndLevel(SysOrgan dictionary) {
        SysOrgan model = this.getById(dictionary.getId());
        if (saveOrUpdateNumLevel(model)) {
            return ResultEnum.NOT_FOUND;
        }
        return ResultEnum.SUCCESS;
    }

    private boolean saveOrUpdateNumLevel(SysOrgan model) {
        if (model == null) {
            return true;
        }
        //#更新数量
        QueryWrapper<SysOrgan> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(SysOrgan::getParentId, model.getId()).eq(SysOrgan::getType, 0);
        Integer count = this.getBaseMapper().selectCount(queryWrapper);
        model.setChildrenNum(count);
        //#更新层级
        if (StringUtils.isEmpty(model.getParentId())) {
            model.setDepth(LongSwingConstants.Number.ONE);
        } else {
            SysOrgan sysOrgan = this.getBaseMapper().selectById(model.getParentId());
            model.setDepth(sysOrgan.getDepth() + LongSwingConstants.Number.ONE);
        }
        //更新path
        QueryWrapper<SysOrgan> queryWrapperPath = new QueryWrapper();
        queryWrapperPath.lambda().eq(SysOrgan::getParentId, model.getId());
        SysOrgan SysOrganParent = this.getBaseMapper().selectOne(queryWrapper);
        if (StringUtils.isEmpty(SysOrganParent.getPath())) {
            model.setPath(SysOrganParent.getId());
        } else {
            model.setPath(SysOrganParent.getPath() + "," + SysOrganParent.getId());
        }
        model.setChildrenNum(count);
        this.saveOrUpdate(model);
        return false;
    }
}
