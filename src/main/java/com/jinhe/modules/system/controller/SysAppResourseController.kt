package com.jinhe.modules.system.controller;


import com.jinhe.common.util.Result
import com.jinhe.common.util.ResultUtil
import com.jinhe.modules.system.entity.SysAppResourse
import com.jinhe.modules.system.service.ISysAppResourseService
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
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
class SysAppResourseController {
    @Autowired
    private var iSysAppResourseService: ISysAppResourseService? = null;

    /**
     * 新增手机菜单
     */
    @ApiOperation(value = "角色新增权限", notes = "角色新增权限")
    @RequestMapping(value = ["addByRoleId/{roleId}"], method = [RequestMethod.POST])
    fun saveOrUpdate(@PathVariable roleId: String?, @RequestBody sysAppResourse: SysAppResourse): Result<*> {
        iSysAppResourseService?.saveOrUpdate(sysAppResourse);
        return ResultUtil.success()
    }
}

