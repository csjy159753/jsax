package com.jinhe.modules.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.jinhe.common.util.Result
import com.jinhe.common.util.ResultUtil
import com.jinhe.modules.sys.entity.Dictionary
import com.jinhe.modules.sys.service.IDictionaryService
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * <p>
 * 数据字典 前端控制器
 * </p>
 *
 * @author rls
 * @since 2020-11-18
 */
@RestController
@RequestMapping("/sys/dictionary")
class DictionaryController {

    @Autowired
    private lateinit var iDictionaryService: IDictionaryService

    /**
     * 根据ID查询机构
     */
    @ApiOperation(value = "根据ID查询机构", notes = "根据ID查询机构")
    @RequestMapping(value = ["saveOrUpdate"], method = [RequestMethod.GET])
    fun saveOrUpdate(@RequestBody dictionary: Dictionary): Result<*>? {
        if (dictionary.type != null) {
            return ResultUtil.error()
        }
        val queryWrapper = QueryWrapper<Dictionary>();
        queryWrapper.eq("type", dictionary.type)
        val list = iDictionaryService.list(queryWrapper)
        if (list.size > 0) {

        } else {
            iDictionaryService.saveOrUpdate(dictionary)
        }

        return ResultUtil.success()
    }
}

