package com.jinhe.modules.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.jinhe.common.util.ListSub
import com.jinhe.common.util.PageFilter
import com.jinhe.common.util.Result
import com.jinhe.common.util.ResultUtil
import com.jinhe.modules.system.entity.SysAppResource
import com.jinhe.modules.system.service.ISysAppResourceService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rls
 * @since 2020-11-11
 */
@RestController
@RequestMapping("/system/sys-app-resourse")
@Api(tags = ["system"])
@Transactional(rollbackFor = [Exception::class])
class SysAppResourceController {
    @Autowired
    private lateinit var iSysAppResourceService: ISysAppResourceService;

    @ApiOperation(value = "角色新增权限", notes = "角色新增权限")
    @RequestMapping(value = ["saveOrUpdate/{roleId}"], method = [RequestMethod.POST])
    fun saveOrUpdate(@PathVariable roleId: String?, @RequestBody sysAppResourse: SysAppResource): Result<*> {
        iSysAppResourceService?.saveOrUpdate(sysAppResourse)
        return ResultUtil.success()
    }


    @ApiOperation(value = "删除手机资源菜单", notes = "删除手机资源菜单")
    @RequestMapping(value = ["remove/{id}"], method = [RequestMethod.POST])
    fun remove(@PathVariable id: String?): Result<*> {
        iSysAppResourceService.removeById(id);
        return ResultUtil.success()
    }

    @ApiOperation(value = "获取手机资源菜单", notes = "删除手机资源菜单")
    @RequestMapping(value = ["list"], method = [RequestMethod.GET])
    fun list(pageFilter: PageFilter): Result<ListSub<SysAppResource>> {
        var page = Page<SysAppResource>(pageFilter.start.toLong(), pageFilter.length.toLong())
        var queryWrapper = QueryWrapper<SysAppResource>();
        queryWrapper.orderByDesc("create_time");
        var iPage = iSysAppResourceService.page(page, queryWrapper);
        return ResultUtil.success(iPage)
    }
}

