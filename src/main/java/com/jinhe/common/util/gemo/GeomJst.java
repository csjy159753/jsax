package com.jinhe.common.util.gemo;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Polygon;

/**
 * @author rls
 */
public class GeomJst {
    /**
     * @param: [xys, lng, lat]
     * xys:经纬度点组成的封闭图形,格式为经度,纬度,经度,纬度
     * lng:经度;
     * lat:纬度
     * @return: java.lang.Boolean
     * @description: 电子围栏多边形判断经纬度点是否在多边形内
     */
    public static Boolean polygonJudgment(String xys, Double lng, Double lat) {
        String[] strings = xys.split(",");
        Coordinate[] coordinates = new Coordinate[strings.length / 2];
        try {
            for (int i = 0; i < strings.length; i += 2) {
                coordinates[i / 2] = new Coordinate(Double.parseDouble(strings[i]), Double.parseDouble(strings[i + 1]));
            }
            GeometryFactory factory = new GeometryFactory();
            if (coordinates.length > 3) {
                LinearRing shell = factory.createLinearRing(coordinates);
                Polygon polygon = factory.createPolygon(shell, null);
                if (polygon.contains(factory.createPoint(new Coordinate(lng, lat)))) {
                    return true;
                }
            }
        } catch (Exception ignored) {
            return false;
        }
        return false;
    }
}
