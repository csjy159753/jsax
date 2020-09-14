package com.jinhe.modules.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.common.annotation.SysLog;
import com.jinhe.common.util.ListSub;
import com.jinhe.common.util.PageFilter;
import com.jinhe.common.util.ResultEnum;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.common.util.Tree.TreeNode;
import  com.jinhe.common.util.Result;
//import com.jinhe.common.vo.Result;
import com.jinhe.modules.system.dto.SysResourceDto;
import com.jinhe.modules.system.entity.SysResource;
import com.jinhe.modules.system.service.ISysResourceService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Cache;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author rls
 * @since 2020-04-13
 */
@RestController
@CrossOrigin
@RequestMapping("/api/SysResource")
@Api(description = "菜单管理", tags = {"system-SysResource"})
public class SysResourceController {
    //记录器
    Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private ISysResourceService ISysResService;

    /**
     * 查询所有菜单（分页）
     **/
    @ApiOperation(value = "查询所有菜单（分页）", notes = "查询所有菜单")
    @RequestMapping(value = "NormalList/{userid}", method = RequestMethod.GET)
    @SysLog(value = "NormalList/{userid}")
    public Result Select_SysRespage(@PathVariable String userid, Page page) {
        ListSub<SysResource> sysResource;
        try {
            sysResource = ISysResService.selectSysResourcepage(userid);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.error("NormalList", e.getMessage());
            return ResultUtil.error(ResultEnum.RESOURCE_SELECT_NOT_FOUND);
        }
        return ResultUtil.success(sysResource);
    }

    /**
     * 根据ID查询所有菜单
     **/
    @ApiOperation(value = "根据ID查询菜单", notes = "根据ID查询菜单")
    @RequestMapping(value = "sysresourc/{id}", method = RequestMethod.GET)
    @SysLog(value = "sysresourc/{id}")
    public Result SelectSysResourcebyid(@PathVariable String id) {
        SysResourceDto sysResourceDto;
        try {
            sysResourceDto = ISysResService.Select_SysRespagebyid(id);
        } catch (Exception e) {
            logger.error("sysresourc", e.getMessage());
            return ResultUtil.error(ResultEnum.RESOURCE_SELECT_NOT_FOUND);
        }
        return ResultUtil.success(sysResourceDto);
    }

    /**
     * 新增菜单
     **/
    @ApiOperation(value = "新增或更新菜单", notes = "新增或更新菜单")
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    @SysLog(value = "saveOrUpdate")
    public Result saveOrUpdate(@RequestBody SysResource sysres) {
        try {
            ISysResService.saveOrUpdate(sysres);
        } catch (Exception e) {
            logger.error("saveOrUpdate", e.getMessage());
            return ResultUtil.error(ResultEnum.RESOURCE_INSERT_ERROR);
        }
        return ResultUtil.success();
    }

    /**
     * 菜单查询父子结构查询
     **/
    @ApiOperation(value = "菜单树状结构查询", notes = "菜单树状结构查询")
    @RequestMapping(value = "parentresource", method = RequestMethod.GET)
    @SysLog(value = "parentresource")
    public Result ParentResourceTree(Page page) {
        List<TreeNode> treeNodes;
        try {
            treeNodes = ISysResService.SysResourceTree(page);
        } catch (Exception e) {
            logger.error("parentresource", e.getMessage());
            return ResultUtil.error(ResultEnum.RESOURCE_SELECT_NOT_FOUND);
        }
        return ResultUtil.success(treeNodes);
    }


    @ApiOperation(value = "根据ID删除菜单", notes = "根据ID删除菜单")
    @RequestMapping(value = "Delete/{id}", method = RequestMethod.DELETE)
    @SysLog(value = "Delete/{id}")
    public Result DeleteOrganByid(@PathVariable String id) {
        try {
            ISysResService.removeById(id);
        } catch (Exception e) {
            logger.error("Delete", e.getMessage());
            return ResultUtil.error(ResultEnum.RESOURCE_DELETE_ERROR);
        }
        return ResultUtil.success();
    }


    @ApiOperation(value = "根据ID更新机构", notes = "根据ID更新机构")
    @RequestMapping(value = "sysrecourcebyid", method = RequestMethod.POST)
    @SysLog(value = "sysrecourcebyid")
    public Result UpdateOrganByid(@RequestBody SysResource sysorgan) {
        try {
            ISysResService.updateById(sysorgan);
        } catch (Exception e) {
            logger.error("sysrecourcebyid", e.getMessage());
            return ResultUtil.error(ResultEnum.ORGAN_UPDATE_ERROR);
        }
        return ResultUtil.success();
    }


  /*  @ApiOperation(value = "更新菜单", notes = "更新菜单")
    @RequestMapping(value = "Modify", method = RequestMethod.PUT)
    @SysLog(value = "Modify")
    public Result modify(@RequestBody SysResource sysRes) {
        try {
            ISysResService.updateById(sysRes);
        } catch (Exception e) {
            logger.error("Modify", e.getMessage());
            return ResultUtil.error(ResultEnum.RESOURCEITEM_UPDATE_ERROR);
        }
        return ResultUtil.success();
    }*/
}
