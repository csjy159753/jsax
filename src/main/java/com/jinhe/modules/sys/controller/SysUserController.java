package com.jinhe.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.common.util.*;
import com.jinhe.config.LongSwingConstants;
import com.jinhe.config.ResultEnum;
import com.jinhe.modules.sys.dto.UserInfoDTO;
import com.jinhe.modules.sys.service.ISysUserService;
import com.jinhe.modules.system.dto.SysPasswordDTO;
import com.jinhe.modules.sys.dto.SysUserDTO;
import com.jinhe.modules.system.entity.SysOrgan;
import com.jinhe.modules.system.entity.SysUser;
import com.jinhe.modules.system.entity.SysUserOrgan;
import com.jinhe.modules.sys.service.ISysOrganService;
import com.jinhe.modules.sys.service.ISysUserOrganService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.jinhe.modules.sys.domian.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author rls
 * @since 2020-10-29
 */
@RestController
@RequestMapping("/sys/sys-user")
@Api(tags = "sys")
@Transactional(rollbackFor = Exception.class)
public class SysUserController {
    @Autowired
    private ISysUserService iSysUserService;
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ISysUserOrganService iSysUserOrganService;
    @Autowired
    private ISysOrganService iSysOrganService;

    /**
     * 新增用户
     *
     * @return
     */
    @ApiOperation(value = "新增用户", notes = "新增用户")
    @RequestMapping(value = "saveUser", method = RequestMethod.POST)
    public Result saveUser(@RequestBody SysUserDTO sysUserDto) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        UserInfo userInfo = new UserInfo();
        // 密码复杂度 10位4选3
        String passwordHash = sysUserDto.getPasswordHash();
        if (!userInfo.CheckPassword(passwordHash)) {
            return ResultUtil.error(ResultEnum.USER_UPDATE_PASSWORD_ERROR);
        }
        ResultEnum resultEnum = userInfo.NormalizedUsername(sysUserDto.getNormalizedUsername());
        if (resultEnum != ResultEnum.USER_NAME_CORRECT) {
            return ResultUtil.success(resultEnum);
        }
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("NORMALIZED_USERNAME", sysUserDto.getNormalizedUsername());
        SysUser sysUser = iSysUserService.getBaseMapper().selectOne(queryWrapper);
        if (sysUser != null) {
            return ResultUtil.error(ResultEnum.USER_NAME_ALREADY_EXISTS);
        }
        sysUserDto.setPasswordHash(EncryptUtil.getInstance().MD5_32(sysUserDto.getPasswordHash()));
        sysUserDto.setId(StringUtils.getGUID());
        sysUser = Mapper.ModelToModel(sysUserDto, SysUser.class);
        iSysUserService.save(sysUser);
        if(sysUserDto.getOrganIds()==null||sysUserDto.getOrganIds().size()==0){
            return ResultUtil.error(ResultEnum.INSERT_USER_ORGAN_ERROR);
        }
        List<SysUserOrgan> organList = new ArrayList<>();
        for (String organId : sysUserDto.getOrganIds()) {
            SysUserOrgan sysUserOrgan = new SysUserOrgan();
            sysUserOrgan.setOrganId(organId);
            sysUserOrgan.setUserId(sysUserDto.getId());
            organList.add(sysUserOrgan);
        }
        iSysUserOrganService.saveBatch(organList);
        return ResultUtil.success();
    }


    /**
     * 重置密码
     *
     * @return
     */
    @ApiOperation(value = "重置密码", notes = "重置密码")
    @RequestMapping(value = "updatePassword", method = RequestMethod.PUT)
    public Result updatePassword(@RequestBody SysPasswordDTO from) {
        UserInfo userInfo = new UserInfo();
        // 密码复杂度 10位4选3
        if (!userInfo.CheckPassword(from.getPassWordHash())) {
            return ResultUtil.error(ResultEnum.USER_UPDATE_PASSWORD_ERROR);
        }
        try {
            SysUser sysUser = iSysUserService.getById(from.getUserId());
            if (sysUser != null) {
                sysUser.setPasswordHash(EncryptUtil.getInstance().MD5_32(from.getPassWordHash()));
                UpdateWrapper<SysUser> userUpdateWrapper = new UpdateWrapper<>();
                userUpdateWrapper.set("PASSWORD_HASH", sysUser.getPasswordHash());
                userUpdateWrapper.eq("ID", sysUser.getId());
                iSysUserService.update(userUpdateWrapper);
                return ResultUtil.success();
            } else {
                return ResultUtil.error(ResultEnum.USER_NOT_FOUND);
            }
        } catch (Exception e) {
            return ResultUtil.error(ResultEnum.PARAMETER_ERROR);
        }

    }


    /**
     * 禁用/恢复用户
     *
     * @return
     */
    @ApiOperation(value = "禁用/恢复用户", notes = "禁用/恢复用户")
    @RequestMapping(value = "updateUserState/{userId}/{state}", method = RequestMethod.PUT)
    public Result updateUserState(@PathVariable String userId, @PathVariable Integer state) {
        try {
            SysUser sysUser = iSysUserService.getById(userId);
            if (sysUser != null) {
                sysUser.setState(state);
                iSysUserService.updateById(sysUser);
                return ResultUtil.success();
            } else {
                return ResultUtil.error(ResultEnum.USER_NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("ableUserById", e.getMessage());
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
    public Result UpdateInfo(@RequestBody SysUserDTO sysUserDto) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (sysUserDto.getOrganIds() == null || sysUserDto.getOrganIds().size() == 0) {
            //用户没有关联机构
            ResultUtil.error(ResultEnum.USER_NOT_RELEVANCY_ORGAN);
        }
        SysUser sysUser = null;
        sysUser = Mapper.ModelToModel(sysUserDto, SysUser.class);
        sysUser.setPasswordHash(null);
        iSysUserService.updateById(sysUser);
        //更新机构
        UpdateWrapper queryWrapper = new UpdateWrapper();
        queryWrapper.in("user_id", sysUserDto.getOrganIds());
        iSysUserOrganService.remove(queryWrapper);
        List<SysUserOrgan> organList = new ArrayList<>();
        List<String> organIds = sysUserDto.getOrganIds();
        for (String organId : organIds) {
            SysUserOrgan sysUserOrgan = new SysUserOrgan();
            sysUserOrgan.setOrganId(organId);
            sysUserOrgan.setUserId(sysUserDto.getId());
            organList.add(sysUserOrgan);
        }
        iSysUserOrganService.saveBatch(organList);
        return ResultUtil.success();
    }


    /**
     * 删除用户
     *
     * @return
     */
    @ApiOperation(value = "删除用户", notes = "删除账户")
    @RequestMapping(value = "/removeById/{id}", method = RequestMethod.DELETE)
    public Result removeById(@PathVariable String id) {
        iSysUserOrganService.removeById(id);
        return ResultUtil.success();
    }

    /**
     * 获取用户列表
     **/
    @ApiOperation(value = "根据机构id获取用户基本信息", notes = "根据机构id获取用户基本信息")
    @RequestMapping(value = "list/{userId}", method = RequestMethod.GET)
    public Result list(@PathVariable String userId, String organId, Integer state, PageFilter pageFilter) {
        SysUser sysUser = iSysUserService.getById(userId);
        if (StringUtils.isEmpty(organId) && !sysUser.getType().equals(LongSwingConstants.USER_TYPE_ADMIN)) {
            return ResultUtil.error();
        }
        if (state == null) {
            state = 1;
        }
        Page<SysUserDTO> page = new Page<>(pageFilter.getStart(), pageFilter.getLength());
        IPage<SysUserDTO> ipage = iSysUserService.listByOrganId(page, organId, state, pageFilter.getKeyWord());
        return ResultUtil.success(ipage);
    }

    /**
     * 根据用户id获取用户信息和机构信息
     **/
    @ApiOperation(value = "根据用户id获取用户信息和机构信息", notes = "根据用户id获取用户信息和机构信息")
    @RequestMapping(value = "getUserInfo/{userId}", method = RequestMethod.GET)
    public Result getUserInfo(@PathVariable String userId) {
        SysUser sysUser = iSysUserService.getById(userId);
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setSysUser(sysUser);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        queryWrapper.select("organ_id");
        List<String> listOrganId = iSysUserOrganService.listObjs(queryWrapper);
        if (listOrganId != null && listOrganId.size() > 0) {
            QueryWrapper queryWrapperOrgan = new QueryWrapper();
            queryWrapperOrgan.in("id", listOrganId);
            List<SysOrgan> listOrgan = iSysOrganService.list(queryWrapperOrgan);
            userInfoDTO.setListOrgan(listOrgan);
        }
        return ResultUtil.success(userInfoDTO);
    }
}
