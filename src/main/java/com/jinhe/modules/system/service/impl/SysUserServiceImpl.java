package com.jinhe.modules.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhe.common.util.Mapper;
import com.jinhe.modules.system.dao.SysUserMapper;
import com.jinhe.modules.system.dao.SysUserOrganMapper;
import com.jinhe.modules.system.dao.SysUserRoleMapper;
import com.jinhe.modules.system.dto.SysUser;
import com.jinhe.modules.system.dto.SysUserDto;
import com.jinhe.modules.system.dto.SysUserOrgan;
import com.jinhe.modules.system.dto.SysUserRole;
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
    private SysUserOrganMapper sysUserOrganMapper;
    private SysUserRoleMapper sysUserRoleMapper;
    //查询所有用户列表
    @Override
    public IPage<SysUserDto> userList(Page<SysUserDto> page, SysUserDto sysUserDto) {
        return sysUserMapper.userList(page, sysUserDto);
    }

   //新增用户
    @Override
    public void addUser(SysUserDto sysUserDto) {
          //获取对象
           SysUser sysUser=new SysUser();
          SysUserRole sysUserRole=new SysUserRole();
           SysUserOrgan sysUserOrgan=new SysUserOrgan();
           //设置User属性
           sysUser.setNickName(sysUserDto.getNickName());
           sysUser.setNormalizedUsername(sysUserDto.getNormalizedUserName());
           sysUser.setPasswordHash(sysUserDto.getPasswordHash());
           sysUser.setRealName(sysUserDto.getRealName());
           sysUser.setNormalizedEmail(sysUserDto.getNormalizedEmail());
           sysUser.setPhoneNumber(sysUserDto.getPhoneNumber());
           //新增用户属性
        sysUserMapper.insert(sysUser);
          String userId=sysUser.getId();
           //获取用户角色ID和机构ID
           List<String> roleIds=sysUserDto.getRoleIds();
           List<String> organIds=sysUserDto.getOrganIds();
           //添加用户角色
        for (String x:roleIds
             ) {

               sysUserRole.setRoleId(x);
               sysUserRole.setUserId(userId);
            System.out.println(sysUserRole);
              sysUserRoleMapper.insert(sysUserRole);


        }
           //添加用户机构
        for (String x:organIds
        ) {
            sysUserOrgan.setOrganId(x);
            sysUserOrgan.setUserId(userId);
           sysUserOrganMapper.insert(sysUserOrgan);

        }


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

        SysUser sysUser=new SysUser();
        SysUserRole sysUserRole=new SysUserRole();
        SysUserOrgan sysUserOrgan=new SysUserOrgan();
        //设置User属性
        sysUser.setId(sysUserDto.getId());
        sysUser.setNickName(sysUserDto.getNickName());
        sysUser.setNormalizedUsername(sysUserDto.getNormalizedUserName());
        sysUser.setRealName(sysUserDto.getRealName());
        sysUser.setNormalizedEmail(sysUserDto.getNormalizedEmail());
        sysUser.setPhoneNumber(sysUserDto.getPhoneNumber());
        //更新用户属性
        sysUserMapper.updateById(sysUser);
        String userId=sysUserMapper.selectIdBynormalizedUserName(sysUserDto.getNormalizedUserName());
        //获取用户角色ID和机构ID
        List<String> roleIds=sysUserDto.getRoleIds();
        List<String> organIds=sysUserDto.getOrganIds();
        //更新用户角色
        for (String x:roleIds
        ) {
            sysUserRole.setRoleId(x);
            sysUserRole.setUserId(userId);
            sysUserRoleMapper.updateById(sysUserRole);
        }
        //更新用户机构
        for (String x:organIds
        ) {
            sysUserOrgan.setOrganId(x);
            sysUserOrgan.setUserId(userId);
            sysUserOrganMapper.updateById(sysUserOrgan);

        }
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
