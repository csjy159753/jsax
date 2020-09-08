package com.jinhe.modules.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinhe.common.annotation.SysLog;
import com.jinhe.common.util.PageFilter;
import com.jinhe.common.util.Result;
import com.jinhe.common.util.ResultEnum;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.modules.system.dto.SysResourceItemDto;
import com.jinhe.modules.system.entity.SysResourceItem;
import com.jinhe.modules.system.service.ISysResourceItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author rls
 * @since 2020-05-19
 */
@RestController
@CrossOrigin
@RequestMapping("/api/SysResourceItem")
@Api(description = "资源 管理", tags = {"system-SysResourceItem"})
public class SysResourceItemController {
    //记录器
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ISysResourceItemService iSysRes;

    /**
     * 根据Id查询资源
     **/
    @ApiOperation(value = "根据Id查询资源", notes = "根据Id查询资源")
    @RequestMapping(value = "List/{id}", method = RequestMethod.GET)
    @SysLog(value = "List/{id}")
    public Result getListById(@PathVariable String id, PageFilter pagefilter) {
        IPage<SysResourceItemDto> sysResource ;
        try {
            Page page = new Page(pagefilter.getStart(), pagefilter.getLength());
          sysResource = iSysRes.getListById(page, id);
        }catch (Exception e){
            logger.error("List", e.getMessage());
            return ResultUtil.error(ResultEnum.RESOURCEITEM_SELECT_NOT_FOUND);
        }

        return ResultUtil.success(sysResource);
    }


    @ApiOperation(value = "新增或更新资源", notes = "新增或更新资源")
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    @SysLog(value = "saveOrUpdate")
    public Result saveOrUpdate(@RequestBody SysResourceItem sysRes) {
        try {
            iSysRes.saveOrUpdate(sysRes);
        } catch (Exception e) {
            logger.error("saveOrUpdate", e.getMessage());
            return ResultUtil.error(ResultEnum.RESOURCEITEM_INSERT_ERROR);
        }
        return ResultUtil.success();
    }


    @ApiOperation(value = "根据Id查询资源", notes = "根据Id查询资源")
    @RequestMapping(value = "Get/{id}", method = RequestMethod.GET)
    @SysLog(value = "Get/{id}")
    public Result getById(@PathVariable String id) {
        SysResourceItem sysResourceItem ;
        try {
            sysResourceItem =    iSysRes.getBaseMapper().selectById(id);
        } catch (Exception e) {
            logger.error("Get", e.getMessage());
            return ResultUtil.error(ResultEnum.RESOURCEITEM_SELECT_NOT_FOUND);
        }
        return ResultUtil.success(sysResourceItem);
    }

    /**
     * 根据Id删除权限
     **/
    @ApiOperation(value = "根据Id删除权限", notes = "根据Id删除权限")
    @RequestMapping(value = "Delete/{id}", method = RequestMethod.DELETE)
    @SysLog(value = "Delete/{id}")
    public Result deleteById(@PathVariable String id) {
        try {
            iSysRes.removeById(id);
        } catch (Exception e) {
            logger.error("Delete", e.getMessage());
            return ResultUtil.error(ResultEnum.RESOURCEITEM_DELETE_ERROR);
        }
        return ResultUtil.success();
    }

  /*  @ApiOperation(value = "更新资源", notes = "更新资源")
    @RequestMapping(value = "Modify", method = RequestMethod.PUT)
    @SysLog(value = "Modify")
    public Result modify(@RequestBody SysResourceItem sysRes) {
        try{
            iSysRes.getBaseMapper().updateById(sysRes);
        }catch (Exception e){
            logger.error("Modify", e.getMessage());
            return ResultUtil.error(ResultEnum.RESOURCEITEM_UPDATE_ERROR);
        }
        return ResultUtil.success();
    }*/
}

