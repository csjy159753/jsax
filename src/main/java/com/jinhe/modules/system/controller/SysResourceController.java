package com.jinhe.modules.system.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.common.vo.Result;
import com.jinhe.modules.system.dto.SysResourceDto;
import com.jinhe.modules.system.entity.SysResource;
import com.jinhe.modules.system.service.ISysResourceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rls
 * @since 2020-04-13
 */
@RestController
@RequestMapping("/system/sys-resource")
public class SysResourceController {
    @Resource
    private ISysResourceService ISysResService;

    /**
     * 查询所有菜单（分页）
     * **/
    @GetMapping("/sys_resource")
    @ApiOperation(value = "查询所有菜单（分页）", notes = "查询所有菜单")

    public Result Select_SysRespage(Page page){
        SysResource sysres = new SysResource();

        Page<SysResource> pagedata = ISysResService.selectSysResourcepage(page);

        return ResultUtil.success(pagedata);
    }

    /**
     * 根据ID查询所有菜单
     * **/
    @GetMapping("/sys_resourc")
    @ApiOperation(value = "查询所有菜单", notes = "查询所有菜单")

    public SysResourceDto Select_SysResourcebyid(String ID){
        SysResourceDto sysresDto = new SysResourceDto();

        sysresDto = ISysResService.Select_SysRespagebyid(ID);

        return sysresDto;
    }

    /**
     * 根据ID查询所有菜单
     * **/
    @PostMapping("/addsys_resource")
    @ApiOperation(value = "查询所有菜单", notes = "查询所有菜单")
    public Integer Select_SysResourcebyid(@RequestBody SysResourceDto sysres){

        Integer intr = ISysResService.sysresourcesave(sysres);

        return intr;
    }


}
