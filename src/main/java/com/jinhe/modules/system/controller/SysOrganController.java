package com.jinhe.modules.system.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.common.util.PageFilter;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.common.util.Tree.TreeNode;
import com.jinhe.common.vo.Result;
import com.jinhe.modules.system.dto.SysOrganDto;
import com.jinhe.modules.system.entity.SysOrgan;
import com.jinhe.modules.system.service.ISysOrganService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiak
 * @since 2020-04-16
 */
@RestController
@RequestMapping("/system/sys-organ")
public class SysOrganController {

    @Resource
    private ISysOrganService Isysorgan;

    /**
     * 查询所有机构（分页）
     **/
    @GetMapping("/sys_organ")
    @ApiOperation(value = "查询所有机构（分页）", notes = "查询所有机构")

    public Result Select_SysOrgpage(PageFilter filter) {
        SysOrgan sysorg = new SysOrgan();
        Page page = new Page(filter.getStart(), filter.getLength());
        Page<SysOrgan> pagedata = Isysorgan.selectPageOrgan(page);

        return ResultUtil.success(pagedata);
    }

    /**
    * 根据ID查询机构
    *
    **/
    @GetMapping("/sysorganbyid{id}")
    @ApiOperation(value = "根据ID查询机构", notes = "根据ID查询机构")
    @ApiImplicitParams(
            @ApiImplicitParam(value="id",name="id",dataType="String")
    )
    public Result SelectSysOrganID(String id) {
            SysOrganDto sysorg = new SysOrganDto();

            sysorg = Isysorgan.selectPageOrganByID(id);

            return ResultUtil.success(sysorg);
    }

    /**
    * 新增机构
    *
    **/
    @PutMapping("/sysorganadd")
    @ApiOperation(value = "新增机构", notes = "新增机构")
    public boolean AddSysOrgan(@RequestBody  SysOrganDto SysOrg){
        boolean flags = false;

        flags = Isysorgan.addsysorgan(SysOrg);

        return flags;
    }

    /**
    * 分页查询机构(父子级)
    *
    **/
    @GetMapping("/sys_organparent")
    @ApiOperation(value = "查询所有机构（树形结构）", notes = "查询所有机构（树形结构）")

    public Result SelectOrganParent(PageFilter pagefilter){

        Page page = new Page(pagefilter.getStart(), pagefilter.getLength());

        List<TreeNode> pagelist = Isysorgan.SelectOrganParent(page);

        return ResultUtil.success(pagelist);

    }

    @DeleteMapping("/sysorganbyid{id}")
    @ApiOperation(value = "根据ID删除机构", notes = "根据ID删除机构")
    @ApiImplicitParams(
            @ApiImplicitParam(value="id",name="id",dataType="String")
    )
    public Integer DeleteOrganByid(String id){

        Integer flags = Isysorgan.DeleteOrganByid(id);

        return flags;

    }
    @PostMapping("/sysorganbyid")
    @ApiOperation(value = "根据ID更新机构", notes = "根据ID更新机构")

    public Integer UpdateOrganByid(@RequestBody SysOrganDto sysorgandto){

        Integer flags = Isysorgan.UpdateOranByid(sysorgandto);

        return flags;
    }
}

