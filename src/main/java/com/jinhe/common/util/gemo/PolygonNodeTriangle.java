//package com.jinhe.common.util.gemo;
//
//
//import org.locationtech.jts.geom.Coordinate;
//import org.locationtech.jts.geom.Polygon;
//import org.locationtech.jts.io.ParseException;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
///**
// * <p>Title : PolygonNodeTriangle </p>
// * <p>Description : 多边形自身节点组成三角形</p>
// *
// * @author huifer
// * @date 2018/10/15
// */
//public class PolygonNodeTriangle {
//    private int NUM = 3;
//    private Set result_p = new HashSet();
//
//    public static void main(String[] args) {
//        //0
//        double[] point1 = new double[]{0, 0};
//        //1
//        double[] point2 = new double[]{10, 0};
//        //2
//        double[] point3 = new double[]{20, 0};
//        //3
//        double[] point4 = new double[]{10, 10};
//
//        List<double[]> allPoint = new ArrayList();
//        allPoint.add(point1);
//        allPoint.add(point3);
//        allPoint.add(point4);
//
//
//        PolygonNodeTriangle polygonCenterPoint = new PolygonNodeTriangle();
//
//        // 外围
//        Polygon waiwei = polygonCenterPoint.waiwei(point1, point3, point4);
//        // 节点三角形
//        List<Polygon> sanjiaoxing = polygonCenterPoint.triangleMothed(allPoint);
//        // 外围内所有三角形
//        List<Polygon> rangeTriangle = polygonCenterPoint.getRangeTriangle(waiwei, sanjiaoxing);
//        // 重心xy
//        double[] gravityCenterXY = polygonCenterPoint.getGravityCenterXY(rangeTriangle);
//
//        System.out.println(rangeTriangle.size());
//        System.out.println("================================================");
//
//        double[] doubles = polygonCenterPoint. polygonGravityPoint("POLYGON((0 0, 20 0, 10 10, 0 0))");
//
//
//    }
//
//    /***
//     * polygon wkt 计算重心
//     * @param wkt
//     * @return
//     */
//    private double[] polygonGravityPoint(String wkt) {
//
//        if (!wkt.startsWith("POLYGON")) {
//            return null;
//        }
//
//        Operation operation = new Operation();
//        // 外围数据转 list<double[]>
//        Polygon waiwei = null;
//        try {
//            waiwei = operation.createPolygonByWKT(wkt);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Coordinate[] coordinates = waiwei.getCoordinates();
//        List<double[]> allP = new ArrayList<>();
//        Arrays.stream(coordinates).forEach(
//                s -> {
//                    double nowX = s.x;
//                    double nowY = s.y;
//                    allP.add(new double[]{nowX, nowY});
//                }
//        );
//
//        List<Polygon> polygons = triangleMothed(allP);
//        List<Polygon> rangeTriangle1 = getRangeTriangle(waiwei, polygons);
//        double area = waiwei.getArea();
//        double[] gravityCenterXY1 = getGravityCenterXY(rangeTriangle1);
//        return gravityCenterXY1;
//    }
//
//
//    /***
//     * 重心值
//     * @param rangeTriangle
//     * @return [x, y]
//     */
//    private double[] getGravityCenterXY(List<Polygon> rangeTriangle) {
//        double xArea = 0.0;
//        double yArea = 0.0;
//        double aArea = 0.0;
//        for (Polygon triangle : rangeTriangle) {
//            Coordinate[] coordinates = triangle.getCoordinates();
//            double area = triangle.getArea();
//            double[] oneGR = triangleCenterOfGravity(coordinates[0], coordinates[1], coordinates[2]);
//            xArea += oneGR[0] * area;
//            yArea += oneGR[1] * area;
//            aArea += area;
//        }
//        System.out.println("重心X  " + xArea / aArea);
//        System.out.println("重心Y  " + yArea / aArea);
//        return new double[]{xArea / aArea, yArea / aArea};
//    }
//
//    /***
//     * 范围内三角形
//     * @param waiwei
//     * @param sanjiaoxing
//     * @return
//     */
//    private List<Polygon> getRangeTriangle(Polygon waiwei, List<Polygon> sanjiaoxing) {
//
//        List<Polygon> triangle = new ArrayList<>();
//        // 判断三角形是否在面内
//        for (int i = 0; i < sanjiaoxing.size(); i++) {
//            Polygon polygon = sanjiaoxing.get(i);
//            boolean within = polygon.within(waiwei);
//            if (within) {
//                triangle.add(polygon);
//            }
//        }
//        return triangle;
//    }
//
//
//    /***
//     * 三角形重心计算
//     * @param a
//     * @param b
//     * @param c
//     * @return
//     */
//    private double[] triangleCenterOfGravity(Coordinate a, Coordinate b, Coordinate c) {
//
//        double gravityX = (a.x + b.x + c.x) / 3;
//        double gravityY = (a.y + b.y + c.y) / 3;
//        double[] result = new double[]{gravityX, gravityY};
//        return result;
//    }
//
//
//    /***
//     * 测试用外包图形
//     * @return
//     */
//    private Polygon waiwei(double[] point1, double[] point3, double[] point4) {
//        List<double[]> ceshimian = new ArrayList();
//        ceshimian.add(point1);
////        ceshimian.add(point2);
////        ceshimian.add(point7);
//        ceshimian.add(point4);
////        ceshimian.add(point6);
////        ceshimian.add(point5);
//        ceshimian.add(point3);
//        String polygonForList = createPolygonForList(ceshimian);
//        Operation op = new Operation();
//        Polygon polygonByWKT = null;
//        try {
//            polygonByWKT = op.createPolygonByWKT(polygonForList);
//            return polygonByWKT;
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /***
//     * 生成所有三角形
//     * @param allPoint
//     * @return
//     */
//    private List<Polygon> triangleMothed(List<double[]> allPoint) {
//        // 索引 -> 点坐标
//        Map<String, double[]> indexOfPoint = new HashMap();
//        for (int i = 0; i < allPoint.size(); i++) {
//            indexOfPoint.put(String.valueOf(i), allPoint.get(i));
//        }
//        // 排序结果
//        sort((List) indexOfPoint.keySet().stream().collect(Collectors.toList()), new HashSet());
//
//        // 删除元素相同后的集合
//
//
//        // 所有三角形
//        List<Polygon> allTriangle = new ArrayList();
//
//
//        for (Object oneDataObj : result_p) {
//            //这一行数据
//            Set oneDataList = (Set) oneDataObj;
//            // 这一行数据的三角形数据
//            List<double[]> trianglePoint = new ArrayList();
//
//            oneDataList.forEach(
//                    s -> trianglePoint.add(indexOfPoint.get(s)
//                    ));
//            Polygon triangle = createTriangle(trianglePoint);
//            if (triangle != null) {
//                allTriangle.add(triangle);
//            }
//        }
//        // 所有三角形结束
//
//
//        return allTriangle;
//
//
//    }
//
//    /***
//     * 从点坐标集合中创建一个面
//     * @param points
//     * @return
//     */
//    private static String createPolygonForList(List<double[]> points) {
//        String end = "))";
//        String res = "POLYGON((";
//        Operation op = new Operation();
//        for (double[] point : points) {
//            String x = Double.toString(point[0]);
//            String y = Double.toString(point[1]);
//            res += x + " " + y + ", ";
//        }
//        res += Double.toString(points.get(0)[0]) + " " + Double.toString(points.get(0)[1]);
//        res += end;
//        try {
//            op.createPolygonByWKT(res);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return res;
//    }
//
//    /***
//     * 创建三角形
//     * @param trianglePoint
//     * @return polygon
//     */
//    private static Polygon createTriangle(List<double[]> trianglePoint) {
//        Operation op = new Operation();
//
//        String triangleWkt;
//        boolean isTri = isTriangle(trianglePoint);
//        if (isTri) {
//            triangleWkt = "POLYGON((" + trianglePoint.get(0)[0] + " " + trianglePoint.get(0)[1] + ", " + trianglePoint.get(1)[0] + " " + trianglePoint.get(1)[1] + ", " + trianglePoint.get(2)[0] + " " + trianglePoint.get(2)[1] + ", " + trianglePoint.get(0)[0] + " " + trianglePoint.get(0)[1] + "))";
//            try {
//                Polygon polygonByWKT = op.createPolygonByWKT(triangleWkt);
//
//                return polygonByWKT;
////                return triangleWkt;
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//
//    /***
//     * 判断三角形
//     * @param trianglePoint
//     * @return
//     */
//    private static boolean isTriangle(List<double[]> trianglePoint) {
//        double[] doubles = trianglePoint.get(0);
//        double[] doubles1 = trianglePoint.get(1);
//        double[] doubles2 = trianglePoint.get(2);
//        double len = Math.sqrt(Math.pow(doubles[0] - doubles1[0], 2) + Math.pow(doubles[1] - doubles1[1], 2));
//        double len1 = Math.sqrt(Math.pow(doubles[0] - doubles2[0], 2) + Math.pow(doubles[1] - doubles2[1], 2));
//        double len2 = Math.sqrt(Math.pow(doubles1[0] - doubles2[0], 2) + Math.pow(doubles1[1] - doubles2[1], 2));
//        if ((len + len1 > len2) && (len + len2 > len1) && (len1 + len2 > len)) {
//            return true;
//        }
//        return false;
//    }
//
//
//    /***
//     * 不重复排列 (元素不相同)
//     * @param datas
//     * @param target
//     */
//    private void sort(List datas, Set target) {
//        if (target.size() == this.NUM) {
//            this.result_p.add(target);
//            return;
//        }
//        for (int i = 0; i < datas.size(); i++) {
//            List newDatas = new ArrayList(datas);
//            Set newTarget = new HashSet(target);
//            newTarget.add(newDatas.get(i));
//            newDatas.remove(i);
//            sort(newDatas, newTarget);
//        }
//    }
//}
//
