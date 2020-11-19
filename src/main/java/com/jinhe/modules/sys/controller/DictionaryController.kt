package com.jinhe.modules.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.jinhe.common.util.Result
import com.jinhe.common.util.ResultUtil
import com.jinhe.common.util.Tree.TreeChildren
import com.jinhe.config.ResultEnum
import com.jinhe.modules.sys.dto.DictionaryDTO
import com.jinhe.modules.sys.entity.Dictionary
import com.jinhe.modules.sys.service.IDictionaryService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

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
@Api(tags = ["sys"])
class DictionaryController {

    @Autowired
    private lateinit var iDictionaryService: IDictionaryService

    /**
     * 保存及更新单个字典
     */
    @ApiOperation(value = "保存及更新单个字典", notes = "保存及更新单个字典")
    @RequestMapping(value = ["saveOrUpdate"], method = [RequestMethod.POST])
    fun saveOrUpdate(@RequestBody dictionary: Dictionary): Result<*>? {
        if (dictionary.type != null) {
            return ResultUtil.error()
        }
        val queryWrapper = QueryWrapper<Dictionary>();
        queryWrapper.eq("type", dictionary.type)
        val list = iDictionaryService.list(queryWrapper)
        if (list.size > 0) {
            if (list[0].type.equals(dictionary.type) && list[0].parentId == dictionary.parentId) {
                iDictionaryService.saveOrUpdate(dictionary)
            } else {
                return ResultUtil.error(ResultEnum.ORGAN_TYPE_ERROR)
            }
        } else {
            iDictionaryService.saveOrUpdate(dictionary)
        }
        return ResultUtil.success()
    }

    /**
     * 获取全部字典
     */
    @ApiOperation(value = "获取全部字典", notes = "获取全部字典")
    @RequestMapping(value = ["list"], method = [RequestMethod.GET])
    fun list(id: String?): Result<List<DictionaryDTO>> {
        return if (id != null) {
            val list = iDictionaryService.list(QueryWrapper<Dictionary>().eq("parent_id", id))
            ResultUtil.success(list) as Result<List<DictionaryDTO>>
        } else {
            val list = iDictionaryService.list();
            val listChildren = TreeChildren().CreateTree(list, DictionaryDTO::class.java)
            ResultUtil.success(listChildren) as Result<List<DictionaryDTO>>
        }

    }

    /**
     * 根据id删除字典
     */
    @ApiOperation(value = "根据id删除字典", notes = "根据id删除字典")
    @RequestMapping(value = ["remove/{id}"], method = [RequestMethod.DELETE])
    fun remove(@PathVariable id: String): Result<*> {
        val count = iDictionaryService.list(QueryWrapper<Dictionary>().eq("parent_id", id))
        return if (count.size > 0) {
            ResultUtil.error(ResultEnum.ORGAN_EXIST_SUBSET_UNABLE_DEL)
        } else {
            iDictionaryService.removeById(id)
            ResultUtil.success()
        }
    }

}

