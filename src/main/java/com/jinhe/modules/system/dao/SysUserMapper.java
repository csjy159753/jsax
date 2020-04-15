package com.jinhe.modules.system.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.jinhe.common.utils.PageUtils;
import com.jinhe.modules.system.dto.SysUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jinhe.modules.system.dto.SysUserDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rls
 * @since 2020-03-12
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select({"select sys_user.id, sys_user.NORMALIZED_USERNAME, sys_user.nick_Name,sys_organ.FULL_NAME as organ_name,sys_role.TAG as role_name,sys_user.CREATE_TIME from (sys_user left join sys_user_role on sys_user.id=sys_user_role.user_id LEFT JOIN sys_role on sys_user_role.ROLE_ID=sys_role.id)left join sys_user_organ on sys_user.id=sys_user_organ.user_id LEFT JOIN sys_organ on sys_user_organ.ORGAN_ID=sys_organ.id"})
    Page<SysUserDto> listAllrls();

    @Select({"select ID,NORMALIZED_USERNAME, 'aaa' as kk_aa  from sys_user"})
    List<SysUserDto> listDemo();

    @Insert({""})
     void addUser(SysUserDto sysUserDto);

    @Select({"select sys_user.NORMALIZED_USERNAME, sys_user.nick_Name,sys_organ.FULL_NAME as organ_name,sys_role.TAG as role_name,sys_user.CREATE_TIME from (sys_user left join sys_user_role on sys_user.id=sys_user_role.user_id LEFT JOIN sys_role on sys_user_role.ROLE_ID=sys_role.id)left join sys_user_organ on sys_user.id=sys_user_organ.user_id LEFT JOIN sys_organ on sys_user_organ.ORGAN_ID=sys_organ.id where sys_organ.FULL_NAME like #{organName} and sys_role.TAG like #{roleName} and sys_user.NORMALIZED_USERNAME like #{normalizedUserName}"})
    List<SysUserDto> selectByWords(String normalizedUserName,String organName,String roleName);

    @Update({""})
    void updateUser(SysUserDto sysUserDto);

    @Update({"update sys_user set passWord_hash=#{newPassword} where id=#{userId} and passWord_hash=#{oldPassword}"})
    void updatePassword(String oldPassword,String newPassword,String userId);

    @Update({"update sys_user set state=#{x} where id=#{userId}"})
    void ableUserById(String userId,int x);

    @Select({"select state from sys_user where id=#{userId}"})
    int selectStateById(String userId);

    @Update({"delete from sys_user where id=#{userId}"})
    void deleteUserById(String userId);

    @Select({"select sys_user.id, sys_user.NORMALIZED_USERNAME, sys_user.nick_Name,sys_organ.FULL_NAME as organ_name,sys_role.TAG as role_name,sys_user.CREATE_TIME from (sys_user left join sys_user_role on sys_user.id=sys_user_role.user_id LEFT JOIN sys_role on sys_user_role.ROLE_ID=sys_role.id)left join sys_user_organ on sys_user.id=sys_user_organ.user_id LEFT JOIN sys_organ on sys_user_organ.ORGAN_ID=sys_organ.id where sys_user.state=1"})
    List<SysUserDto> disableUserList();

}
