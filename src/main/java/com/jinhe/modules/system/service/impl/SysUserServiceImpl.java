package com.jinhe.modules.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhe.modules.system.dao.SysUserMapper;
import com.jinhe.modules.system.dto.SysUser;
import com.jinhe.modules.system.dto.SysUserDto;
import com.jinhe.modules.system.service.ISysUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;


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

    //查询所有用户列表
    @Override
    public IPage<SysUserDto> userList(Page<SysUserDto> page, SysUserDto sysUserDto) {
        return sysUserMapper.userList(page, sysUserDto);
    }

   //新增用户
    @Override
    public void addUser(SysUserDto sysUserDto) {
          sysUserMapper.addUser(sysUserDto);
    }

    //关键字查询
    @Override
    public IPage<SysUserDto> selectByWords(Page<SysUserDto> page, SysUserDto sysUserDto,String normalizedUserName,String organName,String roleName) {

       if(normalizedUserName==null){
          normalizedUserName="";
       }
        if(organName==null){
            organName="";
        }
        if(roleName==null){
            roleName="";
        }

        normalizedUserName="%"+normalizedUserName+"%";
        organName="%"+organName+"%";
        roleName="%"+roleName+"%";
        System.out.println(normalizedUserName+""+organName);
        return sysUserMapper.selectByWords(page, sysUserDto,normalizedUserName,organName,roleName);
    }

    //查询被禁用户列表
    @Override
    public IPage<SysUserDto> disableUserList(Page<SysUserDto> page, SysUserDto sysUserDto) {
        return sysUserMapper.disableUserList(page,sysUserDto);
    }

    //更新用户
    @Override
    public void updateUser(SysUserDto sysUserDto) {
        sysUserMapper.updateUser(sysUserDto);
    }

    //重置密码
    @Override
    public void updatePassword(String oldPassword, String newPassword,String userId) {
        sysUserMapper.updatePassword(oldPassword,newPassword,userId);
    }

    //禁用/恢复账户
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

    //删除用户
    @Override
    public void deleteUserById(String userId) {
        int x=sysUserMapper.selectStateById(userId);
        if(x==1){
            sysUserMapper.deleteUserById(userId);
        }
    }

}
