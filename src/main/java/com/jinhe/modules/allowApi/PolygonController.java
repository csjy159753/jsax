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
    static class EqualDistance {
        /**
         * 这是可以修改的间距
         */
        private static double distance = 0;
        /**
         * 高德地图多边形路径点的坐标
         */
        private List<PointG> points = new ArrayList<PointG>();
        /**
         * 缩小或放大后的坐标
         */
        private List<PointG> new_points = new ArrayList<PointG>();
        /**
         * 边向量的坐标
         */
        private List<PointG> vector_points = new ArrayList<PointG>();
        /**
         * 向量之间的夹角sin值
         */
        private List<Double> points_sin_value = new ArrayList<Double>();
        /**
         * 多边形的中心点坐标
         */
        private PointG points_center = null;

        public EqualDistance(List<PointG> pointList, PointG center) {
            this.points = pointList;
            this.points_center = center;
        }

        /**
         * 计算出每两点之间的单位向量
         */
        public void calc_Side_Vector() {
            int length = this.points.size();
            List<PointG> points = this.points;
            for (int i = 0; i < length; i++) {
                double vector_x = points.get((i + 1) % length).x - points.get(i).x;
                double vector_y = points.get((i + 1) % length).y - points.get(i).y;
                double normal_vector_x = vector_x / Math.sqrt(vector_x * vector_x + vector_y * vector_y);
                double normal_vector_y = vector_y / Math.sqrt(vector_x * vector_x + vector_y * vector_y);
                this.vector_points.add(new PointG(normal_vector_x, normal_vector_y));
            }
        }

        /**
         * 计算夹角的正弦值
         */
        public void calc_Angel() {
            double length = this.vector_points.size();
            List<PointG> normal_vector = this.vector_points;
            for (int i = 0; i < length; i++) {
                double x1 = normal_vector.get((int) ((i + 1) % length)).x;
                double y1 = normal_vector.get((int) ((i + 1) % length)).y;
                double x2 = normal_vector.get(i).x;
                double y2 = normal_vector.get(i).y;
                double sin_theta = x2 * y1 - x1 * y2;
                this.points_sin_value.add(sin_theta);
            }
        }

        /**
         * 计算向内扩展点坐标
         */
        public void calc_new_points() {
            double length = this.vector_points.size();
            List<PointG> normal_vector = this.vector_points;
            List<Double> theta = this.points_sin_value;
            double L = distance;
            for (int i = 0; i < length; i++) {
                PointG point = this.points.get((int) ((i + 1) % length));
                double x1 = normal_vector.get((int) ((i + 1) % length)).x;
                double y1 = normal_vector.get((int) ((i + 1) % length)).y;
                double x2 = -normal_vector.get(i).x;
                double y2 = -normal_vector.get(i).y;
                double add_x = (L / theta.get(i)) * (x1 + x2);
                double add_y = (L / theta.get(i)) * (y1 + y2);
                double new_point_x = point.x - add_x; // 高德地图坐标系与普通坐标系不同， 所以x和y都是减，相反向外扩展都是加
                double new_point_y = point.y - add_y;
                PointG new_point = new PointG(new_point_x, new_point_y);
                this.new_points.add(new_point);
            }
        }
    }

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
