package com.jinhe.modules.system.controller;

import com.jinhe.common.annotation.SysLog;
import com.jinhe.common.util.Result;
import com.jinhe.common.util.ResultEnum;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.modules.system.dto.SysRegionDTO;
import com.jinhe.modules.system.service.ISysRegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author rls
 * @since 2020-04-16
 */
@RestController
@CrossOrigin
@RequestMapping("/noAuthApi/SysRegion")
@Api(description = "行政区(不用授权api)", tags = "noAuthApi-SysRegion")
public class SysRegionNoAuthController {

    /**
     * 记录器
     */
    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ISysRegionService sysRegionService;

    /**
     * 查询行政区列表
     *
     * @return
     */
    @ApiOperation(value = "查询行政区列表", notes = "查询行政区列表")
    @RequestMapping(value = "ListRegion", method = RequestMethod.GET)
    @SysLog(value = "ListRegion")
    public Result regionListTree(@RequestParam(required = false) String code) {
        List<SysRegionDTO> sysRegion = new ArrayList<>();
        try {
            sysRegion = sysRegionService.selectRegionList(code);
        } catch (Exception e) {
            logger.error("ListRegion", e.getMessage());
            return ResultUtil.success(ResultEnum.REGION_NOT_FOUND);
        }
        return ResultUtil.success(sysRegion);
    }

}

