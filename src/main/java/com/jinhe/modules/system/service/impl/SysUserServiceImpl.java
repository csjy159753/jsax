package com.jinhe.modules.system.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhe.common.util.PageFilter;
import com.jinhe.common.util.PageUtils;
import com.jinhe.common.util.Query;
import com.jinhe.modules.system.dto.SysUserDto;
import com.jinhe.modules.system.dto.SysUserVo;
import com.jinhe.modules.system.entity.SysUser;
import com.jinhe.modules.system.dao.SysUserMapper;
import com.jinhe.modules.system.service.ISysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-03-12
 */
@Service
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {


    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public PageUtils queryPage(PageFilter filter, SysUser sysUser) {
        Map<String, Object> params = null;

        return null;
    }

    @Override
    public List<SysUser> listAllrls() {
//       return sysUserMapper.listAllrls();
        return null;
    }

    @Override
    public List<SysUserDto> listDemo() {
//        return sysUserMapper.listDemo();
        return null;
    }

    @Override
    public PageUtils selectPageVo(PageFilter filter, SysUserVo sysUserVo) {
        Page<SysUserVo> page = new Query<SysUserVo>(filter).getPage();
//        Page<SysUserVo> page1 = sysUserMapper.selectPageVo(page, sysUserVo);

       IPage<SysUserVo> li=sysUserMapper.selectPageVo(page, sysUserVo);
        return null;
    }


}
