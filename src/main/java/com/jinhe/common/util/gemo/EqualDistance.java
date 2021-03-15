package com.jinhe.common.util.gemo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 计算多边行 范围扩大或者缩小
 *
 * @author rls
 */
public class EqualDistance {
    private int distance = 5; // 这是可以修改的间距
    private List<Point> points = new ArrayList<Point>(); // 高德地图多边形路径点的坐标
    private List<Point> new_points = new ArrayList<Point>(); // 缩小或放大后的坐标
    private List<Point> vector_points = new ArrayList<Point>();// 边向量的坐标
    private List<Point> points_sin_value = new ArrayList<Point>(); // 向量之间的夹角sin值
    private Point points_center = null; // 多边形的中心点坐标

//    public  EqualDistance(List<Point> pointList, center) {
//        this.points = pointList
//        this.points_center = center
//    }

}
