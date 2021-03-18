package com.jinhe.common.util.gemo;

import java.math.BigDecimal;

public class Point {

    private BigDecimal x;

    private BigDecimal y;

    public Point(double y, double x) {
        this.x = new BigDecimal(x);
        this.y = new BigDecimal(y);
    }

    public Point(BigDecimal y, BigDecimal x) {
        this.x = x;
        this.y = y;
    }

    /**
     * 当前点和顶点之间构成的余弦值平方
     *
     * @param p
     * @return
     */
    private BigDecimal cos2(Point p) {
        BigDecimal vector2 = (p.x.subtract(x).pow(2)).add(p.y.subtract(y).pow(2));
        return (p.x.subtract(x).pow(2)).divide(vector2, 11, BigDecimal.ROUND_HALF_DOWN);
    }

    /**
     * 当前点到顶点之间的Y向量差
     *
     * @param p
     * @return
     */
    private BigDecimal toY(Point p) {
        return p.y.subtract(y);
    }

    /**
     * 当前点到顶点之间的x向量差
     *
     * @param p
     * @return
     */
    private BigDecimal toX(Point p) {
        return p.x.subtract(x);
    }

    /**
     * 1度多少米
     *
     * @return
     */
    private BigDecimal itude1() {
        return new BigDecimal(Math.cos(y.doubleValue())).multiply(new BigDecimal(111194.92474777778)).abs();
    }

    /**
     * 当前顶点到两点之间的距离
     *
     * @param pb 起始点
     * @param pe 结束点
     * @return
     */
    public double distance(Point pb, Point pe) {
        if (pe.toX(pb).doubleValue() == 0) {
            BigDecimal dist2 = pe.toY(this).pow(2);
            return itude1().multiply(new BigDecimal(Math.sqrt(dist2.doubleValue()))).doubleValue();
        } else {
            BigDecimal vector = pe.toY(pb).multiply(toX(pb)).divide(pe.toX(pb), 11, BigDecimal.ROUND_HALF_DOWN).subtract(toY(pb));
            BigDecimal dist2 = pb.cos2(pe).multiply(vector.pow(2));
            return itude1().multiply(new BigDecimal(Math.sqrt(dist2.doubleValue()))).doubleValue();
        }
    }


    /**
     * 求直线外一点到直线上的投影点
     *
     * @param pLine    线上一点
     * @param k        斜率
     * @param pOut     线外一点
     * @param pProject 投影点
     */
    public static void getProjectivePoint(PointF pLine, double k, PointF pOut, PointF pProject) {
        if (k == 0) {//垂线斜率不存在情况
            pProject.x = pOut.x;
            pProject.y = pLine.y;
        } else {
            pProject.x = (float) ((k * pLine.x + pOut.x / k + pOut.y - pLine.y) / (1 / k + k));
            pProject.y = (float) (-1 / k * (pProject.x - pOut.x) + pOut.y);
        }
    }

    /**
     * 求pOut在pLine以及pLine2所连直线上的投影点
     *
     * @param pLine
     * @param pLine2
     * @param pOut
     * @param pProject
     */
    public static void getProjectivePoint(PointF pLine, PointF pLine2, PointF pOut, PointF pProject) {
        double k = 0;
        try {
            k = getSlope(pLine.x, pLine.y, pLine2.x, pLine2.y);
        } catch (Exception e) {
            k = 0;
        }
        getProjectivePoint(pLine, k, pOut, pProject);
    }

    /**
     * 通过两个点坐标计算斜率
     * 已知A(x1,y1),B(x2,y2)
     * 1、若x1=x2,则斜率不存在；
     * 2、若x1≠x2,则斜率k=[y2－y1]/[x2－x1]
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @throws Exception 如果x1==x2,则抛出该异常
     */
    public static double getSlope(double x1, double y1, double x2, double y2) throws Exception {
        if (x1 == x2) {

        }
        return (y2 - y1) / (x2 - x1);
    }

    public static class PointF {
        private double x;
        private double y;

        public PointF() {

        }

        public PointF(double y, double x) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }
    }

    public static void main(String[] args) {
        double decimalMin = -1;
        Point point1 = null;
        Point point2 = null;
        /**
         * [[[[118.363094,31.70013],[118.414421,31.697501],[118.392448,31.655281],[118.351421,31.6696],[118.363094,31.70013]]]]
         * [[118.343611,31.688956]]
         */
        // 地图上画一个多边形
        Point[] points = {
                new Point(31.70013, 118.363094),
                new Point(31.697501, 118.414421),
                new Point(31.655281, 118.392448),
                new Point(31.6696, 118.351421)
        };
        // 地图多边形内随机某一点
        Point p = new Point(31.688956, 118.343611);

        int si = 0;
        // 当前点到多边形各边的距离
        for (int i = 0; i < points.length; i++) {
            double distance = p.distance(points[i], points[i == points.length - 1 ? 0 : i + 1]);
            if (decimalMin < 0 || decimalMin > distance) {
                decimalMin = distance;
                si = i;
            }
            System.out.println("distanceaaa=" + distance + "米");
        }

        point1 = points[si];
        point2 = points[si == points.length - 1 ? 0 : si + 1];
        System.out.println("distance=" + decimalMin + "米");
        System.out.println("point1=" + point1.x.setScale(6, BigDecimal.ROUND_HALF_UP) + ":" + point1.y.setScale(6, BigDecimal.ROUND_HALF_UP));
        System.out.println("point2=" + point2.x.setScale(6, BigDecimal.ROUND_HALF_UP) + ":" + point2.y.setScale(6, BigDecimal.ROUND_HALF_UP));
        PointF pointF1 = new PointF(point1.x.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue(), point1.y.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue());
        PointF pointF2 = new PointF(point2.x.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue(), point2.y.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue());

        System.out.println(PointToPointjingdu(31.688956, 118.343611, pointF1.x, pointF1.y, pointF2.x, pointF2.y));
        System.out.println(PointToPointweidu(31.688956, 118.343611, pointF1.x, pointF1.y, pointF2.x, pointF2.y));

    }

    public static double PointToPointjingdu(double m, double n, double x1, double y1, double x2, double y2) {//计算点到线垂影坐标
        return

                (m * (x2 - x1) * (x2 - x1) + n * (y2 - y1) * (x2 - x1) + (x1 * y2 - x2 * y1) * (y2 - y1))
                        / ((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));


    }

    public static double PointToPointweidu(double m, double n, double x1, double y1, double x2, double y2) {//计算点到线垂影坐标
        return (m * (x2 - x1) * (y2 - y1) + n * (y2 - y1) * (y2 - y1) + (x2 * y1 - x1 * y2) * (x2 - x1))
                / ((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));


    }

    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }
}