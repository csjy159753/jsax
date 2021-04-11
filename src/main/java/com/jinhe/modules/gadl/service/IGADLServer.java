package com.jinhe.modules.gadl.service;

import com.alibaba.fastjson.JSONObject;
import com.jinhe.common.util.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IGADLServer {
    /**
     * 上传shap返回genjson
     *
     * @param file
     * @param fileShx
     * @return
     * @throws IOException
     */
    JSONObject uploadShp(int type, int epsg, int clong, long fe, MultipartFile file, MultipartFile fileShx, MultipartFile fileDbf) throws IOException;

    /**
     * 平面坐标系互转球面坐标系
     *
     * @param type   1 表示平面转球面做坐标系 2 表示球面坐标系转平面坐标系
     * @param epsg   平面坐标系的epsg
     * @param clong  经纬度  一般是117  114  111
     * @param fe     偏移量 一般是50000 350000
     * @param points 需要转换的坐标点
     * @return
     */
    double[][] transformPoints(int type, String epsg, int clong, long fe, double[][] points);
}
