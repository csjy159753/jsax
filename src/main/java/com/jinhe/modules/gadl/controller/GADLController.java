package com.jinhe.modules.gadl.controller;

import com.alibaba.fastjson.JSONObject;
import com.jinhe.common.util.FileUtil;
import com.jinhe.common.util.Result;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.common.util.gemo.EqualDistance;
import com.jinhe.common.util.gemo.entity.PointG;
import com.jinhe.config.BusinessResultEnum;
import com.jinhe.config.ConfigProperty;
import com.jinhe.modules.allowApi.dto.PolygonDTO;
import com.jinhe.modules.gadl.dto.TransFormPointsDTO;
import com.jinhe.modules.gadl.service.IGADLServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.gdal.ogr.Driver;
import org.gdal.osr.CoordinateTransformation;
import org.gdal.osr.SpatialReference;

import org.gdal.ogr.*;
import org.gdal.gdal.*;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/gadl/polygon")
@Api(tags = "gadl")
@Transactional(rollbackFor = Exception.class)
public class GADLController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private IGADLServer igadlServer;

    @ApiOperation(value = "根据shp文件返回json文件", notes = "根据shp文件返回json文件")
    @RequestMapping(value = "/uploadShp/{type}/{epsg}/{clong}/{fe}", method = RequestMethod.POST, consumes = "multipart/*", headers = "content-type=multipart/form-data")
    public Result<String> uploadShp(@PathVariable int type, @PathVariable int epsg, @PathVariable int clong, @PathVariable long fe, @RequestParam("file") MultipartFile... files) throws IOException {
        MultipartFile file = null;
        MultipartFile fileShx = null;
        MultipartFile fileDbf = null;
        for (MultipartFile item : files) {
            if (item.getOriginalFilename().endsWith(".shp")) {
                file = item;
            }
            if (item.getOriginalFilename().endsWith(".shx")) {
                fileShx = item;
            }
            if (item.getOriginalFilename().endsWith(".dbf")) {
                fileDbf = item;
            }
        }
        if (file == null || fileShx == null || fileDbf == null) {
            return ResultUtil.error(BusinessResultEnum.GADL_UPLOAD_LOSE_FILE);
        }

        JSONObject outJson = igadlServer.uploadShp(type, epsg, clong, fe, file, fileShx, fileDbf);
        return ResultUtil.success(outJson);
    }

    @ApiOperation(value = "平面坐标系互转球面坐标系", notes = "平面坐标系互转球面坐标系")
    @RequestMapping(value = "/transformPoints", method = RequestMethod.POST)
    public Result<String> transformPoints(@RequestBody TransFormPointsDTO transFormPointsDTO) {
        double[][] res = igadlServer.transformPoints(transFormPointsDTO.getType(), transFormPointsDTO.getEpsg(), transFormPointsDTO.getClong(), transFormPointsDTO.getFe(), transFormPointsDTO.getPoints());
        return ResultUtil.success(res);
    }
}
