package com.jinhe.modules.allowApi;

import com.jinhe.common.util.Result;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.common.util.gemo.EqualDistance;
import com.jinhe.common.util.gemo.entity.PointG;
import com.jinhe.modules.allowApi.dto.PolygonDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/allowApi/polygon")
@Api(tags = "allowApi")
@Transactional(rollbackFor = Exception.class)
public class PolygonController {

    @ApiOperation(value = "计算多边形", notes = "计算多边形")
    @RequestMapping(value = "/computedPolygon", method = RequestMethod.POST)
    public Result<List<PointG>> computedPolygon(@RequestBody PolygonDTO polygonDTO) {
        EqualDistance.distance = polygonDTO.getDistance();
        List<PointG> pointPixelArr = polygonDTO.getList();
        EqualDistance equalDis = EqualDistance.getEqualDistance(pointPixelArr);
        equalDis.calculate();
        return ResultUtil.success(equalDis.new_points);
    }
}
