package com.jinhe.modules.system.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.common.util.EntityUtils;
import com.jinhe.common.util.Mapper;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.common.util.Tree.TreeNode;
import com.jinhe.common.vo.Result;
import com.jinhe.modules.system.dto.SysOrganDto;
import com.jinhe.modules.system.dto.SysResourceDto;
import com.jinhe.modules.system.entity.SysResource;
import com.jinhe.modules.system.service.ISysResourceService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rls
 * @since 2020-04-13
 */
@RestController
@CrossOrigin
@RequestMapping("/system/sys-resource")
@Api(description = "菜单管理",tags = {"sysresource-ontroller"})
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
    @GetMapping("/sysresourc/{id}")
    @ApiOperation(value = "根据ID查询菜单", notes = "根据ID查询菜单")
    @ApiImplicitParams(
            @ApiImplicitParam(value="id",name="id",dataType="String")
    )
    public SysResourceDto SelectSysResourcebyid(@PathVariable String id){
        SysResourceDto sysresDto = new SysResourceDto();

        sysresDto = ISysResService.Select_SysRespagebyid(id);

        return sysresDto;
    }

    /**
     * 新增菜单
     * **/
    @PostMapping("/addsys_resource")
    @ApiOperation(value = "新增菜单", notes = "新增菜单")

    public boolean Save_SysResource(@RequestBody SysResourceDto sysres){


        boolean flags = ISysResService.sysresourcesave(sysres);

        return flags;
    }

    /**
    *
    *  菜单查询父子结构查询
    **/
    @GetMapping("/parentresource")
    @ApiOperation(value = "菜单树状结构查询", notes = "菜单树状结构查询")

    public Result ParentResourceTree(Page page){



        List<TreeNode> treelist = ISysResService.SysResourceTree(page);

        return ResultUtil.success(treelist);
    }

    @DeleteMapping("/sysrecourcebyid/{id}")
    @ApiOperation(value = "根据ID删除菜单", notes = "根据ID删除菜单")
    @ApiImplicitParams(
            @ApiImplicitParam(value="id",name="id",dataType="String")
    )
    public Integer DeleteOrganByid(@PathVariable String id){

        Integer flags = ISysResService.DeleteResourceByid(id);

        return flags;

    }
    @PostMapping("/sysrecourcebyid")
    @ApiOperation(value = "根据ID更新机构", notes = "根据ID更新机构")

    public Integer UpdateOrganByid(@RequestBody SysResourceDto sysorgandto){

        Integer flags = ISysResService.UpdateOranByid(sysorgandto);

        return flags;
    }
}
