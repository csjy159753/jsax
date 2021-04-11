package com.jinhe.testdemo;

import org.gdal.osr.CoordinateTransformation;
import org.gdal.osr.SpatialReference;
import org.junit.Test;


public class MyTest3 {
    @Test
    public void testT1() {
        SetGCS();
        CoordinateTransform();
    }

    private static void SetGCS() {
        SpatialReference osr = new SpatialReference("");
        osr.SetWellKnownGeogCS("WGS84");
    }

    private static void CoordinateTransform() {
        SpatialReference src = new SpatialReference("");
        src.SetGeocCS("EPSG:4490");
        src.SetTM(0, 117, 1.0, 500000, 0);
        SpatialReference dst = src.CloneGeogCS();

        CoordinateTransformation ct = new CoordinateTransformation(src, dst);
        double[] dZa = new double[3];

        ct.TransformPoint(dZa, 545064.588, 3812410.256, 0);
        System.out.println(String.format("%s,%s,%s", dZa[0], dZa[1], dZa[2]));

        CoordinateTransformation cta = new CoordinateTransformation(dst, src);
        double[] dZaa = new double[3];

        cta.TransformPoint(dZaa, 34.43848700623854, 117.49032390479873);
        System.out.println(String.format("%s,%s,%s", dZaa[0], dZaa[1], dZaa[2]));
        double[][] dZb = new double[2][2];
        dZb[0] = new double[]{39446474.82, 03627174.96000000089407, 0};
        dZb[1] = new double[]{39446474.82, 3627174.96000000089407};
        double[][] dZba = new double[][]{
                {34.43848700623854, 117.49032390479873},
                {34.43848700623854, 117.49032390479873}
        };
        cta.TransformPoints(dZba);
        System.out.println(dZba);
    }

}
