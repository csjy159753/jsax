package com.jinhe.modules.allowApi;

import com.jinhe.common.config.ResultEnum;
import com.jinhe.common.util.Result;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.common.util.gemo.GeomJst;
import com.jinhe.common.util.gemo.Point;
import com.jinhe.modules.allowApi.dto.LocationDTO;
import com.jinhe.modules.allowApi.dto.LocationRequestDTO;
import com.jinhe.modules.allowApi.dto.PointDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取点的位置
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/allowApi/location")
@Api(tags = "allowApi")
@Transactional(rollbackFor = Exception.class)
public class LocationController {

    @ApiOperation(value = "获取点到面的位置信息", notes = "获取点到面的位置信息")
    @RequestMapping(value = "getLocation", method = RequestMethod.POST)
    public Result<LocationDTO> getLocation(@RequestBody LocationRequestDTO locationRequestDTO) {
        List<PointDTO> pointDTOList = locationRequestDTO.getPointList();
        PointDTO pointDTO = locationRequestDTO.getPoint();

        // 面数据处理
        List<Point.PointF> pointFs = new ArrayList<>();
        StringBuilder xysStr = new StringBuilder();
        if (pointDTOList.size() < 2) {
            return ResultUtil.error(ResultEnum.PARAMETER_ERROR);
        }
        for (int i = 0; i < pointDTOList.size(); i++) {
            if (!pointDTOList.get(i).checkValue()) {
                return ResultUtil.error(ResultEnum.PARAMETER_ERROR);
            }
            pointFs.add(pointDTOList.get(i).toPointF());
            xysStr.append(pointDTOList.get(i).getX() + "," + pointDTOList.get(i).getY());
            if (i < pointDTOList.size() - 1) {
                xysStr.append(",");
            }
        }

        // 点数据处理
        if (!pointDTO.checkValue()) {
            return ResultUtil.error(ResultEnum.PARAMETER_ERROR);
        }
        Point point = pointDTO.toPoint();

        LocationDTO locationDTO = new LocationDTO();
        // 判断是否在面内
        Boolean insideFlag = GeomJst.polygonJudgment(xysStr.toString(), Double.valueOf(pointDTO.getX()), Double.valueOf(pointDTO.getY()));
        locationDTO.setInsideFlag(insideFlag);

        // 获取最小距离
        double minDecimal = -1;
        int si = 0;
        for (int i = 1; i < pointFs.size(); i++) {
            double distance = point.distance(new Point(new BigDecimal(pointFs.get(i - 1).getY()), new BigDecimal(pointFs.get(i - 1).getX())),
                    new Point(new BigDecimal(pointFs.get(i).getY()), new BigDecimal(pointFs.get(i).getX())));
            if (minDecimal < 0 || minDecimal > distance) {
                minDecimal = distance;
                si = i;
            }
        }
        locationDTO.setDistance(String.valueOf(minDecimal));

        // 获取投影点
        Point.PointF minPoint1 = pointFs.get(si - 1);
        Point.PointF minPoint2 = pointFs.get(si);
        Point.PointF pPoint = new Point.PointF();
        Point.getProjectivePoint(minPoint1, minPoint2, pointDTO.toPointF(), pPoint);
        locationDTO.setPointP(new PointDTO(String.valueOf(pPoint.getY()), String.valueOf(pPoint.getX())));

        return ResultUtil.success(locationDTO);
    }
}
