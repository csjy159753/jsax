package com.jinhe.modules.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.common.util.Result;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.common.util.Tree.TreeNode;
import com.jinhe.modules.system.service.ISysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.jinhe.common.annotation.SysLog;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author rls
 * @since 2020-04-15
 */
@RestController
@CrossOrigin
@RequestMapping("/system/sys-log")
@Api(description = "日志",tags = "system-sys-log")
public class SysLogController {
    @Autowired
    ISysLogService iSysLogService;

    /**
     * 需要 Token 验证的接口
     */
    @ApiOperation(value = "List分页测试", notes = "List分页测试")
    @RequestMapping(value = "list",method = RequestMethod.GET)
    @SysLog(value = "list")
    public Result List(Page page) {
        com.jinhe.modules.system.entity.SysLog sysLog = new com.jinhe.modules.system.entity.SysLog();
        IPage<SysLog> pageData = iSysLogService.selectPageVo(page, sysLog);
        return ResultUtil.success(pageData);
    }
    /**
     * 需要 Token 验证的接口
     */
    @ApiOperation(value = "ListTree分页测试", notes = "ListTree分页测试")
    @RequestMapping(value = "ListTree",method = RequestMethod.GET)
    @com.jinhe.common.annotation.SysLog(value = "ListTree")
    public Result ListTree(HashMap map) {
        if(map==null){
            map=new HashMap();
        }
        map.put("roleId","21");

        List<TreeNode> list= iSysLogService.selectSysRoleVo(map);
        return ResultUtil.success(list);
    }
}

