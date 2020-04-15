package com.jinhe.modules.system.service.impl;

import com.alibaba.druid.sql.PagerUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jinhe.common.utils.PageUtils;
import com.jinhe.common.utils.Query;
import com.jinhe.modules.system.dao.SysUserMapper;
import com.jinhe.modules.system.dto.SysRole;
import com.jinhe.modules.system.dto.SysUser;
import com.jinhe.modules.system.dto.SysUserDto;
import com.jinhe.modules.system.service.ISysUserService;
import org.apache.commons.lang.StringUtils;
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

  /*  @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");

        Page<SysUser> page = this.selectPage(new Query<SysUser>(params).getPage(), new EntityWrapper<SysUser>().like(StringUtils.isNotBlank(key), "username", key));

        return new PageUtils(page);
    }*/


    @Override
    public PageUtils queryPage() {

        Page<SysUserDto> page =sysUserMapper.listAllrls();

        return new PageUtils(page);
    }

   /* @Override
    public List<SysUserDto> listAllrls() {
      return sysUserMapper.listAllrls();
    }*/

    @Override
    public List<SysUserDto> listDemo() {
        return sysUserMapper.listDemo();

    }

    @Override
    public void addUser(SysUserDto sysUserDto) {
          sysUserMapper.addUser(sysUserDto);
    }

    @Override
    public List<SysUserDto> selectByWords(String normalizedUserName,String organName,String roleName) {
        return sysUserMapper.selectByWords(normalizedUserName, organName, roleName);
    }

    @Override
    public void updateUser(SysUserDto sysUserDto) {
        sysUserMapper.updateUser(sysUserDto);
    }

    @Override
    public void updatePassword(String oldPassword, String newPassword,String userId) {
        sysUserMapper.updatePassword(oldPassword,newPassword,userId);
    }

    @Override
    public void ableUserById(String userId) {
        int x=sysUserMapper.selectStateById(userId);
        if(x==0) {
            x = 1;
        }
        else{
            x=0;
      }
        sysUserMapper.ableUserById(userId,x);
    }

    @Override
    public void deleteUserById(String userId) {
        int x=sysUserMapper.selectStateById(userId);
        if(x==1){
            sysUserMapper.deleteUserById(userId);
        }
    }

    @Override
    public List<SysUserDto> disableUserList() {
      return   sysUserMapper.disableUserList();
    }
}
