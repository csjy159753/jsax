package com.jinhe.modules.system.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.common.annotation.SysLog;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.common.util.Tree.TreeNode;
import com.jinhe.common.vo.Result;

import com.jinhe.modules.system.dto.SysRegion;
import com.jinhe.modules.system.service.ISysRegionService;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rls
 * @since 2020-04-16
 */
@RestController
@RequestMapping("/system/sys-region")
public class SysRegionController {

    @Resource
    private ISysRegionService sysRegionService;

    /**
     * 查询行政区列表
     * @return
     */
    @GetMapping("/regionListTree")
    @ApiOperation(value = "查询行政区列表", notes = "查询行政区列表")
    public Result regionListTree(Page page) {
        List<TreeNode> regionList= sysRegionService.selectRegionList(null);
        return ResultUtil.success(regionList);

    }

    /**
     *新增行政区
     * @return
     */
    @ApiOperation(value="新增行政区", notes="新增行政区")
    @RequestMapping(value = "regionList", method = RequestMethod.PUT)
    @SysLog(value = "测试注解日志切面新增行政区")
    public void addRegion(@RequestBody SysRegion sysregion){
        sysRegionService.addRegion(sysregion) ;
    }

    /**
     *更新行政区
     * @return
     */
    @ApiOperation(value="更新行政区", notes="更新行政区")
    @RequestMapping(value = "regionList", method = RequestMethod.POST)
    @SysLog(value = "测试注解日志切面更新行政区")
    public void updateRegion(@RequestBody SysRegion sysregion){
        sysRegionService.updateRegion(sysregion) ;
    }
    /**
     *删除行政区
     * @return
     */
    @ApiOperation(value="删除行政区", notes="删除行政区")
    @RequestMapping(value = "regionList/{id}", method = RequestMethod.DELETE)
    @SysLog(value = "测试注解日志切面删除行政区")
    public void deleteRegion(String id){
        sysRegionService.deleteRegion(id);
    }

}

