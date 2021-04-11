package com.jinhe.modules.gadl.service.imp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jinhe.common.exception.CustomException;
import com.jinhe.common.util.FileUtil;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.config.BusinessResultEnum;
import com.jinhe.config.ConfigProperty;
import com.jinhe.modules.gadl.service.IGADLServer;
import org.gdal.gdal.gdal;
import org.gdal.ogr.DataSource;
import org.gdal.ogr.Driver;
import org.gdal.ogr.ogr;
import org.gdal.osr.CoordinateTransformation;
import org.gdal.osr.SpatialReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@Service
public class GADLServerImpl implements IGADLServer {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private ConfigProperty configProperty;


    /**
     * 导入shp文件和shx文件导出genjson 字符串
     *
     * @param file
     * @param fileShx
     * @return
     * @throws IOException
     */
    @Override
    public JSONObject uploadShp(int type, int epsg, int clong, long fe,MultipartFile file, MultipartFile fileShx, MultipartFile fileDbf) throws IOException {
        String dirPath = configProperty.GetAbsolutelyUpload();
        long time = new Date().getTime();
        String ogiginalPath = File.separator + time + File.separator + file.getOriginalFilename();
        File ogiginalFile = new File(dirPath + ogiginalPath);

        String ogiginalPathShx = File.separator + time + File.separator + fileShx.getOriginalFilename();
        File ogiginalFileShx = new File(dirPath + ogiginalPathShx);

        String ogiginalPathDbf = File.separator + time + File.separator + fileDbf.getOriginalFilename();
        File ogiginalFileDbf = new File(dirPath + ogiginalPathDbf);
        try {
            if (!ogiginalFile.getParentFile().exists()) {
                ogiginalFile.getParentFile().mkdirs();
            }
            file.transferTo(ogiginalFile);

            if (!ogiginalFileShx.getParentFile().exists()) {
                ogiginalFileShx.getParentFile().mkdirs();
            }
            fileShx.transferTo(ogiginalFileShx);

            if (!ogiginalFileDbf.getParentFile().exists()) {
                ogiginalFileDbf.getParentFile().mkdirs();
            }
            fileDbf.transferTo(ogiginalFileDbf);
        } catch (IOException e) {
            throw e;
        }
        if (!ogiginalFile.exists() || !ogiginalFileShx.exists()) {
            throw new CustomException(BusinessResultEnum.GADL_UPLOAD_LOSE_FILE);

        }
        // 注册所有的驱动
        ogr.RegisterAll();
        // 为了支持中文路径，请添加下面这句代码
        gdal.SetConfigOption("GDAL_FILENAME_IS_UTF8", "YES");
        // 为了使属性表字段支持中文，请添加下面这句
        gdal.SetConfigOption("SHAPE_ENCODING", "CP936");

        String strVectorFile = ogiginalFile.getAbsolutePath();

        //打开文件
        DataSource ds = ogr.Open(strVectorFile, 0);
        if (ds == null) {
            logger.error("gadl", "打开文件失败！");
        }
        logger.info("gadl", "打开文件失败！");
        Driver dv = ogr.GetDriverByName("GeoJSON");
        if (dv == null) {
            logger.error("gadl", "打开驱动失败！");
        }
        logger.info("gadl", "打开文件失败！");
        System.out.println("打开驱动成功！");
        String path = strVectorFile.replace(".shp", ".json");
        DataSource ds1 = dv.CopyDataSource(ds, path);
        ds1.delete();
        String jsonStr = FileUtil.readJsonFile(path);

        SpatialReference src = new SpatialReference("");
        src.SetGeocCS("EPSG:" + epsg);
        src.SetTM(0, clong, 1.0, fe, 0);
        SpatialReference dst = src.CloneGeogCS();
        CoordinateTransformation ct = null;
        if (type == 1) {
            ct = new CoordinateTransformation(src, dst);
        } else {
            ct = new CoordinateTransformation(dst, src);
        }
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
        return outJson;
    }

    @Override
    public double[][] transformPoints(int type, String epsg, int clong, long fe, double[][] points) {
        SpatialReference src = new SpatialReference("");
        src.SetGeocCS("EPSG:" + epsg);
        src.SetTM(0, clong, 1.0, fe, 0);
        SpatialReference dst = src.CloneGeogCS();
        CoordinateTransformation ct = null;
        if (type == 1) {
            ct = new CoordinateTransformation(src, dst);
        } else {
            ct = new CoordinateTransformation(dst, src);
        }
        ct.TransformPoints(points);
        return points;
    }


}
