package com.jinhe.modules.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinhe.common.util.Mapper;
import com.jinhe.common.util.ResultEnum;
import com.jinhe.common.util.StringUtils;
import com.jinhe.modules.system.dao.*;
import com.jinhe.modules.system.dto.*;
import com.jinhe.modules.system.entity.*;
import com.jinhe.modules.system.entity.SysUser;
import com.jinhe.modules.system.entity.SysUserOrgan;
import com.jinhe.modules.system.entity.SysUserRole;
import com.jinhe.modules.system.service.ISysUSerRoleService;
import com.jinhe.modules.system.service.ISysUserOrganService;
import com.jinhe.modules.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


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

    //记录器
    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysUserOrganMapper sysUserOrganMapper;
    @Resource
    private ISysUserOrganService iSysUserOrganService;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;
    @Resource
    private ISysUSerRoleService iSysUSerRoleService;
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysOrganMapper sysOrganMapper;
    @Resource
    private SysUserHisMapper sysUserHisMapper;

    //查询用户列表
    @Override
    public IPage<SysUserDto> selectUserList(Page<SysUserDto> page, String normalizedUserName, String organId, String roleId, Integer state, String userId) {
        SysUser sysUser = sysUserMapper.selectById(userId);
        if (sysUser == null) {
            return null;
        }
        if (state == null) {
            state = 0;
        }
        if (sysUser.getType() != 99 && StringUtils.isEmpty(organId) && StringUtils.isEmpty(roleId)) {
            return null;
        }
        IPage<SysUserDto> iPage = sysUserMapper.selectUserByOrganIdRole(page, organId, roleId, state, normalizedUserName);
        return iPage;

    }

    //新增用户
    @Override
    public int addUser(SysUserDtoNew sysUserDto) {
        //获取对象
        SysUser sysUser = new SysUser();
        try {
            sysUser = Mapper.ModelToModel(sysUserDto, SysUser.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        List<SysUserOrgan> organList = new ArrayList<SysUserOrgan>();
        List<SysUserRole> roleList = new ArrayList<SysUserRole>();

        String userId = sysUser.getId();
        //获取用户角色ID和机构ID
        List<String> roleIds = sysUserDto.getRoleIds();
        List<String> organIds = sysUserDto.getOrganIds();


        //新增用户属性
        sysUser.setState(0);
        sysUser.setType(0);
        int insert = sysUserMapper.insert(sysUser);
        if (insert < 0) {
            return 1;
        } else {
            //添加用户角色
            for (String roleId : roleIds) {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setRoleId(roleId);
                sysUserRole.setUserId(userId);
                String uuid = StringUtils.getGUID();
                sysUserRole.setId(uuid);
                roleList.add(sysUserRole);
            }
            boolean i2 = iSysUSerRoleService.saveBatch(roleList);
            if (!i2) {
                return 2;
            } else {
                //添加用户机构
                for (String organId : organIds) {
                    SysUserOrgan sysUserOrgan = new SysUserOrgan();
                    sysUserOrgan.setOrganId(organId);
                    sysUserOrgan.setUserId(userId);
                    String uuid = StringUtils.getGUID();
                    sysUserOrgan.setId(uuid);
                    organList.add(sysUserOrgan);
                }
                boolean i = iSysUserOrganService.saveBatch(organList);
                if (!i) {
                    return 3;
                } else {
                    return 4;
                }
            }
        }
    }


    //删除用户
    @Override
    public ResultEnum deleteUserById(String userId) throws InstantiationException, IllegalAccessException {
        SysUser sysUser = sysUserMapper.selectById(userId);
        if (sysUser == null) {
            return ResultEnum.USER_NOT_FOUND;
        }
        SysUserHis sysUserHis = Mapper.ModelToModel(sysUser, SysUserHis.class);
        sysUserOrganMapper.deleteByUserId(userId);
        sysUserRoleMapper.deleteByUserId(userId);
        this.removeById(userId);
        sysUserHisMapper.insert(sysUserHis);
        return ResultEnum.SUCCESS;
    }

    //根据id查询
    public SysUser selectById(String id) {
        return sysUserMapper.selectById(id);
    }


    @Override
    public boolean updateAvatarById(String userId, String avatarUrl) {
        return sysUserMapper.updateAvatarById(userId, avatarUrl);
    }

    @Override
    public int SaveOrUpdateRole(String userId, String roleId) {
        sysUserRoleMapper.deleteByUserIdAndRoleId(userId, roleId);
        com.jinhe.modules.system.entity.SysUserRole sysUserOrgan = new com.jinhe.modules.system.entity.SysUserRole();
        sysUserOrgan.setUserId(userId);
        sysUserOrgan.setRoleId(roleId);
        return sysUserRoleMapper.insert(sysUserOrgan);
    }

    @Override
    public int SaveOrUpdateOrgan(String userId, String organId) {
        sysUserOrganMapper.deleteByUserIdAndOrganId(userId, organId);
        com.jinhe.modules.system.entity.SysUserOrgan sysUserOrgan = new com.jinhe.modules.system.entity.SysUserOrgan();
        sysUserOrgan.setUserId(userId);
        sysUserOrgan.setOrganId(organId);

//        QueryWrapper<SysUserOrgan> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("USER_ID", userId);
//        List<SysUserOrgan> list = sysUserOrganMapper.selectList(queryWrapper);
        return sysUserOrganMapper.insert(sysUserOrgan);

    }
}
