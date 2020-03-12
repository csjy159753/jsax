package com.jinhe.modules.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jinhe.common.utils.PageUtils;
import com.jinhe.common.utils.Query;
import com.jinhe.modules.sys.entity.SysLogEntity;
import com.jinhe.modules.system.entity.SysUser;
import com.jinhe.modules.system.dao.SysUserMapper;
import com.jinhe.modules.system.service.ISysUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rls
 * @since 2020-03-12
 */
@Service
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {



    @Resource
    private  SysUserMapper sysUserMapper;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");

        Page<SysUser> page = this.selectPage(new Query<SysUser>(params).getPage(), new EntityWrapper<SysUser>().like(StringUtils.isNotBlank(key), "username", key));

        return new PageUtils(page);
    }

    @Override
    public List<SysUser> listAllrls() {
       return sysUserMapper.listAllrls();
    }
}
