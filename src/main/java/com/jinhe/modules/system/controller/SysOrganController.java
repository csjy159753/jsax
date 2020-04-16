package com.jinhe.modules.system.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.common.util.PageFilter;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.common.util.Tree.TreeNode;
import com.jinhe.common.vo.Result;
import com.jinhe.modules.system.entity.SysOrgan;
import com.jinhe.modules.system.service.ISysOrganService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
        Page<SysOrgan> pagedata = Isysorgan.selectSysOrganpage(page);

        return ResultUtil.success(pagedata);
    }


}

