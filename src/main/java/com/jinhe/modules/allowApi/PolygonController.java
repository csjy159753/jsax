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

import java.util.ArrayList;
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
        double[] d1 = new double[2];
        for (PointG pointG : pointPixelArr) {
            d1[0] += pointG.x;
            d1[1] += pointG.y;
        }
        d1[0] = d1[0] / pointPixelArr.size();
        d1[1] = d1[1] / pointPixelArr.size();
        PointG centerPixel = new PointG(d1[0], d1[1]);
        EqualDistance equalDis = new EqualDistance(pointPixelArr, centerPixel);
        equalDis.calc_Side_Vector();
        equalDis.calc_Angel();
        equalDis.calc_new_points();
        return ResultUtil.success(equalDis.new_points);
    }
}
