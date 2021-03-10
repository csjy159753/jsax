package com.jinhe.modules.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.common.config.ResultEnum;
import com.jinhe.common.util.*;
import com.jinhe.common.util.Tree.TreeChildren;
import com.jinhe.common.config.SystemResultEnum;
import com.jinhe.modules.sys.dto.DictionaryDTO;
import com.jinhe.modules.system.entity.Dictionary;
import com.jinhe.modules.sys.service.IDictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 数据字典 前端控制器
 * </p>
 *
 * @author rls
 * @since 2020-11-19
 */
@RestController
@RequestMapping("/sys/dictionary")
@Api(tags = "sys")
public class DictionaryController {
    @Autowired
    private IDictionaryService iDictionaryService;


    /**
     * 保存及更新单个字典
     */
    @ApiOperation(value = "保存及更新单个字典", notes = "保存及更新单个字典")
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    public Result saveOrUpdate(@RequestBody Dictionary dictionary) {
        ResultEnum resultEnum = null;
        if (dictionary.getId() == null) {
            resultEnum = iDictionaryService.saveDictionary(dictionary);

        } else {
            resultEnum = iDictionaryService.updateDictionary(dictionary);
        }

        return ResultUtil.Info(resultEnum);
    }

    /**
     * 查询字典获取自己全部列表
     */
    @ApiOperation(value = "查询字典获取自己全部列表", notes = "查询字典获取自己全部列表")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result<List<DictionaryDTO>> listAll(String types) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(Dictionary::getType, types.split(","));
        List<Dictionary> li = iDictionaryService.list(queryWrapper);
        List<DictionaryDTO> listTree = new TreeChildren().CreateTree(li, DictionaryDTO.class);
        List<DictionaryDTO> resultList = listTree.stream().filter(s-> s.getParentId() == null).collect(Collectors.toList());
        return ResultUtil.success(resultList);
    }

    /**
     * 获取分页获取单类型字典
     *
     * @return
     */
    @ApiOperation(value = "获取全部字典", notes = "获取全部字典")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Result<ListSub<Dictionary>> list(String id, String type, PageFilter pageFilter) {
        Page page = new Page(pageFilter.getStart(), pageFilter.getLength());
        QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<>();
        if (type != null) {
            queryWrapper.lambda().like(Dictionary::getType, type);
        }
        if (pageFilter != null && type != null) {
            queryWrapper.lambda().like(Dictionary::getName, pageFilter.getKeyWord());
        }
        if (id == null) {
            queryWrapper.lambda().isNull(Dictionary::getParentId);

        } else {
            queryWrapper.lambda().eq(Dictionary::getParentId, id);
        }
        IPage<Dictionary> iPage = iDictionaryService.page(page, queryWrapper);
        return ResultUtil.success(iPage);
    }

    /**
     * 根据id删除字典
     */
    @ApiOperation(value = "根据id删除字典", notes = "根据id删除字典")
    @RequestMapping(value = "remove/{id}", method = RequestMethod.DELETE)
    public Result remove(@PathVariable String id) {
//        int count = iDictionaryService.count(new QueryWrapper<Dictionary>().lambda().eq(Dictionary::getParentId, id));
//        if (count > 0) {
//            return ResultUtil.error(ResultEnum.DICTIONARY_EXIST_SUBSET_UNABLE_DEL);
//        } else {
//
//        }
        Dictionary dictionary = iDictionaryService.getById(id);
        if (dictionary.getSystem()) {
            return  ResultUtil.error(SystemResultEnum.DICTIONARY_TYPE_SYSTEM_NOT_DEL);
        }
        if (dictionary == null) {
            return  ResultUtil.error(ResultEnum.NOT_FOUND);
        }
        iDictionaryService.removeById(id);
        iDictionaryService.saveOrUpdateChildrenNumAndLevel(dictionary.getParentId());
        return ResultUtil.success();
    }
}

