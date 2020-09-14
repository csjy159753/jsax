package com.jinhe.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.common.annotation.SysLog;
import com.jinhe.common.util.*;
import com.jinhe.modules.system.dto.*;
import com.jinhe.modules.system.entity.*;
import com.jinhe.modules.system.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author rls
 * @since 2020-03-12
 */
@RestController
@CrossOrigin
@RequestMapping("/api/SysUser")
@Api(description = "用户管理", tags = "system-SysUser")
public class SysUserController {

    //记录器
    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ISysUserService sysUserService;
    @Resource
    private ISysUserOrganService sysUserOrganService;
    @Resource
    private ISysUSerRoleService sysUserRoleService;
    @Resource
    private ISysRoleService sysRoleService;
    @Resource
    private ISysOrganService sysOrganService;

    public static final String PATTEN = "^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_]+$)(?![a-z0-9]+$)(?![a-z\\W_]+$)(?![0-9\\W_]+$)[a-zA-Z0-9\\W_]{10,}$";

    /**
     * 查询用户列表
     *
     * @return
     */
    @ApiOperation(value = "查询用户列表", notes = "查询用户列表")
    @RequestMapping(value = "/List/{userId}", method = RequestMethod.GET)
    @SysLog(value = "测试注解日志切面关键字查询selectUserList")
    public Result selectUserList(PageFilter filter, String keywords, String organIds, String roleIds, Integer state, @PathVariable String userId) {
        IPage<SysUserDto> userList;
        try {
            Page page = new Page(filter.getStart(), filter.getLength());
            userList = sysUserService.selectUserList(page, keywords, organIds, roleIds, state, userId);
        } catch (Exception e) {
            logger.error("delete", e.getMessage());
            return ResultUtil.success(ResultEnum.NOT_FOUND);
        }
        return ResultUtil.success(userList);
    }

    /**
     * 新增用户
     *
     * @return
     */
    @ApiOperation(value = "新增用户", notes = "新增用户")
    @RequestMapping(value = "AddUser", method = RequestMethod.POST)
    @SysLog(value = "测试注解日志切面新增用户addUser")
    public Result addUser(@RequestBody SysUserDtoNew sysUserDto) {

        // 密码复杂度 10位4选3
        String passwordHash = sysUserDto.getPasswordHash();
        boolean f = passwordHash.contains("012")
                || passwordHash.contains("123")
                || passwordHash.contains("234")
                || passwordHash.contains("345")
                || passwordHash.contains("456")
                || passwordHash.contains("567")
                || passwordHash.contains("678")
                || passwordHash.contains("789")
                || passwordHash.contains("987")
                || passwordHash.contains("876")
                || passwordHash.contains("765")
                || passwordHash.contains("654")
                || passwordHash.contains("543")
                || passwordHash.contains("432")
                || passwordHash.contains("321")
                || passwordHash.contains("210");
        if (f || !passwordHash.matches(PATTEN)) {
            return ResultUtil.error(ResultEnum.USER_UPDATE_PASSWORD_ERROR);
        }

        if ((sysUserDto.getNormalizedUsername() == null || "".equals(sysUserDto.getNormalizedUsername()))
                || (sysUserDto.getPasswordHash() == null || "".equals(sysUserDto.getPasswordHash()))) {
            return ResultUtil.error(ResultEnum.USER_ACCOUNT_OR_PASSWORD_ISNULL);
        }
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("NORMALIZED_USERNAME", sysUserDto.getNormalizedUsername());
        SysUser sysUser = sysUserService.getBaseMapper().selectOne(queryWrapper);
        if (sysUser != null) {
            return ResultUtil.error(ResultEnum.USER_NAME_ALREADY_EXISTS);
        }
//        sysUserDto.setPasswordHash(DigestUtils.md5DigestAsHex(sysUserDto.getPasswordHash().getBytes()));
        sysUserDto.setPasswordHash(EncryptUtil.getInstance().MD5_32(sysUserDto.getPasswordHash()));
        String s = UUID.randomUUID().toString();
        sysUserDto.setId(s.replace("-", ""));
        int flag = sysUserService.addUser(sysUserDto);
        if (flag == 1) {
            return ResultUtil.error(ResultEnum.INSERT_USER_ERROR);
        } else if (flag == 2) {
            return ResultUtil.error(ResultEnum.INSERT_USER_ROLE_ERROR);
        } else if (flag == 3) {
            return ResultUtil.error(ResultEnum.INSERT_USER_ORGAN_ERROR);
        } else {
            return ResultUtil.success();
        }
    }


    /**
     * 重置密码
     *
     * @return
     */
    @ApiOperation(value = "重置密码", notes = "重置密码")
    @RequestMapping(value = "ModifyByOrganRole/{userId}", method = RequestMethod.PUT)
    @SysLog(value = "测试注解日志切面重置密码updatePassword")
    public Result updatePassword(@RequestBody SysUser user, @PathVariable String userId) {
        String passwordHash = user.getPasswordHash();
        // 密码复杂度 10位4选3
        boolean flag = passwordHash.contains("012")
                || passwordHash.contains("123")
                || passwordHash.contains("234")
                || passwordHash.contains("345")
                || passwordHash.contains("456")
                || passwordHash.contains("567")
                || passwordHash.contains("678")
                || passwordHash.contains("789")
                || passwordHash.contains("987")
                || passwordHash.contains("876")
                || passwordHash.contains("765")
                || passwordHash.contains("654")
                || passwordHash.contains("543")
                || passwordHash.contains("432")
                || passwordHash.contains("321")
                || passwordHash.contains("210");
        if (flag || !passwordHash.matches(PATTEN)) {
            return ResultUtil.error(ResultEnum.USER_UPDATE_PASSWORD_ERROR);
        }
        try {
            SysUser sysUser = sysUserService.selectById(userId);
            if (sysUser != null) {

                sysUser.setPasswordHash(EncryptUtil.getInstance().MD5_32(passwordHash));
                sysUserService.updateById(sysUser);
                return ResultUtil.success();
            } else {
                return ResultUtil.error(ResultEnum.USER_NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("updatePassword", e.getMessage());
            return ResultUtil.error(ResultEnum.PARAMETER_ERROR);
        }

    }


    /**
     * 禁用/恢复用户
     *
     * @return
     */
    @ApiOperation(value = "禁用/恢复用户", notes = "禁用/恢复用户")
    @RequestMapping(value = "ableUserById/{userId}/{state}", method = RequestMethod.PUT)
    @SysLog(value = "测试注解日志切面禁用/禁用/恢复用户ableUserById")
    public Result ableUserById(@PathVariable String userId, @PathVariable Integer state) {
        try {
            SysUser sysUser = sysUserService.selectById(userId);
            if (sysUser != null) {
                sysUser.setState(state);
                sysUserService.updateById(sysUser);
                return ResultUtil.success();
            } else {
                return ResultUtil.error(ResultEnum.USER_NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("ableUserById", e.getMessage());
            return ResultUtil.error(ResultEnum.PARAMETER_ERROR);
        }
    }

    /**
     * 更新信息
     *
     * @return
     */
    @ApiOperation(value = "更新信息", notes = "更新信息")
    @RequestMapping(value = "UpdateInfo", method = RequestMethod.PUT)
    @SysLog(value = "UpdateInfo")
    public Result UpdateInfo(@RequestBody SysUserDtoNew sysUserDto) {


        if (sysUserDto.getAvatarUrl() != null) {
            SysUser sysUserNew = null;
            try {
                sysUserNew = Mapper.ModelToModel(sysUserDto, SysUser.class);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
            boolean flag = sysUserService.updateById(sysUserNew);
            if (flag = false) {
                return ResultUtil.error(ResultEnum.INSERT_OR_UPDATE_HEADPORTRAIT_ERROR);
            }

        }

        //更新机构
        List<SysUserOrgan> organList = new ArrayList<SysUserOrgan>();
        List<String> organIds = sysUserDto.getOrganIds();
        for (String organId : organIds) {

            SysUserOrgan sysUserOrgan = new SysUserOrgan();

            String s = UUID.randomUUID().toString();

            sysUserOrgan.setId(s.replace("-", ""));
            sysUserOrgan.setOrganId(organId);
            sysUserOrgan.setUserId(sysUserDto.getId());
            organList.add(sysUserOrgan);
        }
        int i = sysUserOrganService.deleteByUserId(sysUserDto.getId());
        boolean b = sysUserOrganService.insertUserOrgan(organList);
        if (b = false) {
            return ResultUtil.error(ResultEnum.ORGAN_UPDATE_ERROR);
        } else {
            List<SysUserRole> roleList = new ArrayList<SysUserRole>();
            List<String> roleIds = sysUserDto.getRoleIds();
            for (String roleId : roleIds) {
                SysUserRole sysUserRole = new SysUserRole();
                String s = UUID.randomUUID().toString();
                String uuid = s.replace("-", "");
                sysUserRole.setId(uuid);
                sysUserRole.setRoleId(roleId);
                sysUserRole.setUserId(sysUserDto.getId());
                roleList.add(sysUserRole);
            }
            int i1 = sysUserRoleService.deleteByUserId(sysUserDto.getId());
            boolean b1 = sysUserRoleService.insertUserRole(roleList);
            if (b1 = false) {
                return ResultUtil.error(ResultEnum.ROLE_UPDATE_ERROR);
            } else {
                if (sysUserDto.getState() != null && sysUserDto.getState() == 3) {
                    deleteUserById(sysUserDto.getId());
                }

                return ResultUtil.success();
            }
        }

    }


    /**
     * 删除用户
     *
     * @return
     */
    @ApiOperation(value = "删除用户", notes = "删除账户")
    @RequestMapping(value = "/Delete/{id}", method = RequestMethod.DELETE)
    @SysLog(value = "测试注解日志切面删除账户deleteUserById")
    public Result deleteUserById(@PathVariable String id) {
        try {
            sysUserService.deleteUserById(id);
        } catch (Exception e) {
            logger.error("Delete", e.getMessage());
            return ResultUtil.error(ResultEnum.USER_DELETE_ERROR);
        }
        return ResultUtil.success();
    }


    @ApiOperation(value = "查找user和关联的role、organ", notes = "查找user和关联的role、organ")
    @RequestMapping(value = "/UserInfo/{userId}", method = RequestMethod.GET)
    @SysLog(value = "UserInfo/{userId}")
    public Result UserInfo(@PathVariable String userId) {
        SysUser sysUser = sysUserService.selectById(userId);

        if (sysUser == null) {
            return ResultUtil.error(ResultEnum.NOT_FOUND);
        } else {
            SysUserDto sysUserDto = null;
            try {
                sysUserDto = Mapper.ModelToModel(sysUser, SysUserDto.class);
            } catch (IllegalAccessException e) {
                logger.error("UserInfo", e.getMessage());
                e.printStackTrace();
            } catch (InstantiationException e) {
                logger.error("UserInfo", e.getMessage());
                e.printStackTrace();
            }

            Page page = new Page(0, 10);
            List<SysRole> sysRoles = sysRoleService.selectRolesByUserId(page, userId);
            List<SysOrgan> sysOrgans = sysOrganService.SelectOrgansByUserId(userId, page);

            SysUserRoleOrganDto sysUserRoleOrganDto = new SysUserRoleOrganDto();
            sysUserRoleOrganDto.setSysUser(sysUserDto);
            sysUserRoleOrganDto.setRole(sysRoles);
            sysUserRoleOrganDto.setOrgan(sysOrgans);
            return ResultUtil.success(sysUserRoleOrganDto);
        }

    }

    @ApiOperation(value = "更新用户角色", notes = "更新用户角色如果之前存在子角色会删除子角色type=0删除1新增会删除包含子角色")
    @RequestMapping(value = "/SaveOrUpdateRole/{type}/{userId}/{roleId}", method = RequestMethod.GET)
    @SysLog(value = "SaveOrUpdateRole/{type}/{userId}/{roleId}")
    public Result SaveOrUpdateRole(@PathVariable int type, @PathVariable String userId, @PathVariable String roleId) {
        if (type == 1) {
            sysUserService.SaveOrUpdateRole(userId, roleId);
            List<SysRole> listSysRole = sysRoleService.selectRolesByUserId(new Page(0, 1000), userId);
            return ResultUtil.success(listSysRole);
        } else if (type == 0) {
            QueryWrapper<com.jinhe.modules.system.entity.SysUserRole> wrapper = new QueryWrapper<>();
            sysUserRoleService.remove(wrapper);
            List<SysRole> listSysRole = sysRoleService.selectRolesByUserId(new Page(0, 1000), userId);
            return ResultUtil.success(listSysRole);
        }
        return ResultUtil.error(ResultEnum.PARAMETER_ERROR);
    }

    @ApiOperation(value = "更新用户机构", notes = "更新用户机构如果之前存在子角色会删除子角色type=0删除1新增会删除包含子机构")
    @RequestMapping(value = "/SaveOrUpdateOrgan/{type}/{userId}/{roleId}", method = RequestMethod.GET)
    @SysLog(value = "SaveOrUpdateOrgan/{type}/{userId}/{roleId}")
    public Result SaveOrUpdateOrgan(@PathVariable int type, @PathVariable String userId, @PathVariable String roleId) {
        if (type == 1) {
            sysUserService.SaveOrUpdateOrgan(userId, roleId);
            List<SysRole> listSysRole = sysRoleService.selectRolesByUserId(new Page(0, 1000), userId);
            return ResultUtil.success(listSysRole);
        } else if (type == 0) {
            QueryWrapper<com.jinhe.modules.system.entity.SysUserOrgan> wrapper = new QueryWrapper<>();
            sysUserOrganService.remove(wrapper);
            List<SysOrgan> listSysRole = sysOrganService.SelectOrgansByUserId(userId, new Page(0, 1000));
            return ResultUtil.success(listSysRole);
        }
        return ResultUtil.error(ResultEnum.PARAMETER_ERROR);
    }
}
