package com.jinhe.modules.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.common.util.Mapper;
import com.jinhe.modules.system.dao.SysRegionMapper;
import com.jinhe.modules.system.dao.SysUserMapper;
import com.jinhe.modules.system.dto.*;
import com.jinhe.modules.system.dao.SysOrganMapper;
import com.jinhe.modules.system.entity.SysOrgan;
import com.jinhe.modules.system.entity.SysUser;
import com.jinhe.modules.system.service.ISysOrganService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-04-16
 */
@Service
public class SysOrganServiceImpl extends ServiceImpl<SysOrganMapper, SysOrgan> implements ISysOrganService {
    @Resource
    private SysOrganMapper sysOrganMapper;
    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysRegionMapper sysRegionMapper;

    //记录器
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public IPage<SysOrganDto> SelectOrgan(Page<SysOrganDto> page, String userId, String organId) {
        // 根据父机构查询子机构
        SysUser sysUser = sysUserMapper.selectById(userId);
        if (sysUser == null) {
            return null;
        }
        return sysOrganMapper.selectOrgan(page, organId);
    }

    @Override
    @Transactional
    public Integer DeleteOrganByOrganId(String userId, String organId) {
        Integer flags = 0;
        try {
            sysOrganMapper.deleteByPer(userId, organId);
            sysOrganMapper.deleteByUser(userId, organId);
            flags = sysOrganMapper.deleteById(organId);
            return flags;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Integer UpdateOranByid(SysOrgan dto) {
        Integer flags = this.sysOrganMapper.updateById(dto);
        return flags;
    }

    public Integer insert(SysOrgan organ) {
        int insert = sysOrganMapper.insert(organ);
        return insert;
    }

    //根据userId查询
    public List<SysOrgan> SelectOrgansByUserId(String userId, Page page) {
        SysUser sysUser = sysUserMapper.selectById(userId);
        Integer type = sysUser.getType();
        if (type == null || type == 99) {
            List<SysOrgan> sysOrgans = new ArrayList<>();
            return sysOrgans;
        } else {
            List<SysOrgan> sysOrgans = sysOrganMapper.SelectOrgansByUserId(userId, page);
            return sysOrgans;
        }
    }

    @Override
    public List<SysOrgan> GetOrganSubset(List<String> organIds) {
        return sysOrganMapper.GetOrganSubset(organIds,null,null);
    }

    /**
     *  刷新机构表 (Sys_Organ表数据初始化，只执行一次) 慎用
     */
    @Override
    @Transactional
    public void initSysOrganTable() {
         /*
        List<SysOrgan> organList = new ArrayList<SysOrgan>();

        Date date = new Date();
        QueryWrapper<SysRegion> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("code","32");
        queryWrapper.orderByAsc("code");
        List<SysRegion> regionList = sysRegionMapper.selectList(queryWrapper);
        int i = 1;
        for (SysRegion region: regionList) {

            String parentId =null;
            for (SysOrgan o : organList) {
                if(o.getCode().equals(region.getParentCode())){
                    parentId = o.getId();
                    break;
                }
            }

            // 创建湾长
            SysOrgan organ = new SysOrgan();
            organ.setId(StringUtils.getGUID());
            organ.setParentId(parentId);
            organ.setType(0);
            organ.setCode(region.getCode());
            organ.setName(region.getName()+"湾长");
            organ.setRegionCode(region.getCode());
            organ.setRegionName(region.getFullName().replaceAll(" ",""));
            organ.setState(0);
            organ.setSort(0);
            organ.setDepth(region.getLevelInfo());
            organ.setCreateTime(date);
            organList.add(organ);
            sysOrganMapper.insert(organ);

            // 创建湾长办
            SysOrgan organ2 = new SysOrgan();
            organ2.setId(StringUtils.getGUID());
            organ2.setParentId(parentId);
            organ2.setType(1);
            organ2.setCode(region.getCode());
            organ2.setName(region.getName()+"湾长办");
            organ2.setRegionCode(region.getCode());
            organ2.setRegionName(region.getFullName().replaceAll(" ",""));
            organ2.setState(0);
            organ2.setSort(0);
            organ2.setDepth(region.getLevelInfo());
            organ2.setCreateTime(date);
            organList.add(organ2);
            sysOrganMapper.insert(organ2);
            logger.info("插入"+ i++ +"条数据成功");
        }
         */
    }

}
