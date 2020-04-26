package com.jinhe.modules.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.common.util.PageUtils;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.common.util.Tree.TreeNode;
import com.jinhe.common.vo.Result;
import com.jinhe.modules.system.dto.SysUserVo;
import com.jinhe.modules.system.entity.SysLog;
import com.jinhe.modules.system.service.ISysLogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class SysLogController {
    @Autowired
    ISysLogService iSysLogService;

    /**
     * 需要 Token 验证的接口
     */
    @GetMapping("/list")
    @ApiOperation(value = "List分页测试", notes = "List分页测试")
    public Result List(Page page) {
        SysLog sysLog = new SysLog();
        IPage<SysLog> pageData = iSysLogService.selectPageVo(page, sysLog);
        return ResultUtil.success(pageData);
    }
    /**
     * 需要 Token 验证的接口
     */
    @GetMapping("/ListTree")
    @ApiOperation(value = "ListTree分页测试", notes = "ListTree分页测试")
    public Result ListTree(HashMap map ) {
        if(map==null){
            map=new HashMap();
        }
        map.put("userId","superAdmin");

        List<TreeNode> list= iSysLogService.selectSysRoleVo(map);
        return ResultUtil.success(list);
    }
}

