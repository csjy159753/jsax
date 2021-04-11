package com.jinhe.testdemo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jinhe.common.util.FileUtil;
import org.gdal.ogr.Driver;
import org.gdal.osr.CoordinateTransformation;
import org.gdal.osr.SpatialReference;
import org.junit.Test;

import org.gdal.ogr.*;
import org.gdal.gdal.*;

public class MyTest4 {
    @Test
    public void testT1() {

        CoordinateTransform();
    }

    private static void CoordinateTransform() {
        SpatialReference src = new SpatialReference("");
        src.SetGeocCS("EPSG:4490");
        src.SetTM(0, 117, 1.0, 39500000, 0);
        SpatialReference dst = src.CloneGeogCS();

        CoordinateTransformation ct = new CoordinateTransformation(src, dst);
        double[] dZa = new double[3];


        // 注册所有的驱动
        ogr.RegisterAll();
        // 为了支持中文路径，请添加下面这句代码
        gdal.SetConfigOption("GDAL_FILENAME_IS_UTF8", "YES");
        // 为了使属性表字段支持中文，请添加下面这句
        gdal.SetConfigOption("SHAPE_ENCODING", "CP936");

        String strVectorFile = "E:\\公司\\2021\\矿山越界\\淮南市矿山界限shp2000\\淮南市.shp";

        //打开文件
        DataSource ds = ogr.Open(strVectorFile, 0);
        if (ds == null) {
            System.out.println("打开文件失败！");
            return;
        }

        System.out.println("打开文件成功！");
        Driver dv = ogr.GetDriverByName("GeoJSON");
        if (dv == null) {
            System.out.println("打开驱动失败！");
            return;
        }
        System.out.println("打开驱动成功！");
        DataSource ds1 = dv.CopyDataSource(ds, "D:\\test\\node.json");
        ds1.delete();
        String jsonStr = FileUtil.readJsonFile("D:\\test\\node.json");
        JSONObject outJson = JSONObject.parseObject(jsonStr);
        JSONArray featuresJSONArray = outJson.getJSONArray("features");
        for (int i = 0; i < featuresJSONArray.size(); i++) {
            JSONObject jsonObject = featuresJSONArray.getJSONObject(i).getJSONObject("geometry");
            if (jsonObject.getString("type").equals("Polygon")) {
                JSONArray coordinates = jsonObject.getJSONArray("coordinates");
                for (int j = 0; j < coordinates.size(); j++) {
                    for (int m = 0; m < coordinates.getJSONArray(j).size(); m++) {
                        double[] dZaa = new double[3];
                        JSONArray arr = coordinates.getJSONArray(j).getJSONArray(m);
                        ct.TransformPoint(dZaa, arr.getDoubleValue(0), arr.getDoubleValue(1), 0);
                        arr.set(0, dZaa[0]);
                        arr.set(1, dZaa[1]);
                    }
                }
            } else if (jsonObject.getString("type").equals("MultiPolygon")) {
                JSONArray coordinatesA = jsonObject.getJSONArray("coordinates");
                for (int f = 0; f < coordinatesA.size(); f++) {
                    JSONArray coordinates = coordinatesA.getJSONArray(f);
                    for (int j = 0; j < coordinates.size(); j++) {
                        for (int m = 0; m < coordinates.getJSONArray(j).size(); m++) {
                            double[] dZaa = new double[3];
                            JSONArray arr = coordinates.getJSONArray(j).getJSONArray(m);
                            ct.TransformPoint(dZaa, arr.getDoubleValue(0), arr.getDoubleValue(1), 0);
                            arr.set(0, dZaa[0]);
                            arr.set(1, dZaa[1]);
                        }
                    }
                }
            } else if (jsonObject.getString("type").equals("MultiLineString")) {
                JSONArray coordinates = jsonObject.getJSONArray("coordinates");
                for (int j = 0; j < coordinates.size(); j++) {
                    for (int m = 0; m < coordinates.getJSONArray(j).size(); m++) {
                        double[] dZaa = new double[3];
                        JSONArray arr = coordinates.getJSONArray(j).getJSONArray(m);
                        ct.TransformPoint(dZaa, arr.getDoubleValue(0), arr.getDoubleValue(1), 0);
                        arr.set(0, dZaa[0]);
                        arr.set(1, dZaa[1]);
                    }
                }
            } else if (jsonObject.getString("type").equals("LineString")) {
                JSONArray coordinates = jsonObject.getJSONArray("coordinates");

                for (int m = 0; m < coordinates.size(); m++) {
                    double[] dZaa = new double[3];
                    JSONArray arr = coordinates.getJSONArray(m);
                    ct.TransformPoint(dZaa, arr.getDoubleValue(0), arr.getDoubleValue(1), 0);
                    arr.set(0, dZaa[0]);
                    arr.set(1, dZaa[1]);
                }

            } else if (jsonObject.getString("type").equals("MultiPoint")) {
                JSONArray coordinates = jsonObject.getJSONArray("coordinates");

                for (int m = 0; m < coordinates.size(); m++) {
                    double[] dZaa = new double[3];
                    JSONArray arr = coordinates.getJSONArray(m);
                    ct.TransformPoint(dZaa, arr.getDoubleValue(0), arr.getDoubleValue(1), 0);
                    arr.set(0, dZaa[0]);
                    arr.set(1, dZaa[1]);
                }

            } else if (jsonObject.getString("type").equals("Point")) {
                JSONArray coordinates = jsonObject.getJSONArray("coordinates");
                double[] dZaa = new double[3];
                ct.TransformPoint(dZaa, coordinates.getDoubleValue(0), coordinates.getDoubleValue(1), 0);
                coordinates.set(0, dZaa[0]);
                coordinates.set(1, dZaa[1]);
            }
        }
        System.out.println(outJson.toString());
    }

}
