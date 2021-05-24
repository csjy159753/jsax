package com.jinhe.modules.City.controller;


import com.jinhe.common.util.ListSub;
import com.jinhe.common.util.Result;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.common.util.access.MdbfileUtils;
import com.jinhe.modules.City.entity.CjaxCity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/City/cjax-sanshenganxian")
@Api(tags="sanshenganxian")
public class SanShengAnXianController {

    private static String filepath = "c:\\item\\1.mdb";

    @ApiOperation(value = "查询所有三生岸线有关信息")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Result<ListSub<CjaxCity>> list() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException {
        //String  filepath ="d:\\item\\1.mdb";
        List<Map<String, Object>> select =
                MdbfileUtils.select(filepath, "select  * from 三生岸线" , null);
        for (Map<String, Object> stringObjectMap : select) {
            System.out.println(stringObjectMap.toString());
        }
        return ResultUtil.success(select);
    }

    @ApiOperation(value = "查询三生岸线类型")
    @RequestMapping(value = "listForType", method = RequestMethod.GET)
    public Result<ListSub<CjaxCity>> listForType() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException {
        //String  filepath ="d:\\item\\1.mdb";
        List<Map<String, Object>> select =
                MdbfileUtils.select(filepath, "select DISTINCT 三生岸线.岸线类型 from 三生岸线" , null);
        for (Map<String, Object> stringObjectMap : select) {
            System.out.println(stringObjectMap.toString());
        }
        return ResultUtil.success(select);
    }

    @ApiOperation(value = "查询三生岸线类型及长度和比例")
    @RequestMapping(value = "lenAndPerForType", method = RequestMethod.GET)
    public Result<ListSub<CjaxCity>> lenAndPerForType(@RequestParam("cityName")String cityName) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException {
        //String  filepath ="d:\\item\\1.mdb";
        String sql="SELECT DISTINCT 三生岸线.岸线类型,Sum(三生岸线.Shape_Length) as 岸线长度,round(Sum(三生岸线.Shape_Length)/(SELECT Sum(三生岸线.Shape_Length) FROM 三生岸线)*100 ,2) as 比例\n" +
                "FROM 三生岸线\n" +
                "group by 岸线类型";
        List<Map<String, Object>> select =
                MdbfileUtils.select(filepath,sql, null);
        for (Map<String, Object> stringObjectMap : select) {
            System.out.println(stringObjectMap.toString());
        }
        return ResultUtil.success(select);
    }
//     后面改
//    @ApiOperation(value = "按城市查询三生岸线类型及长度和比例")
//    @RequestMapping(value = "lenAndPerForTypeDependOnCity", method = RequestMethod.GET)
//    public Result<ListSub<CjaxCity>> lenAndPerForTypeDependOnCity() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException {
//        String  filepath ="d:\\item\\1.mdb";
//        String sql="SELECT DISTINCT 三生岸线.岸线类型,Sum(三生岸线.Shape_Length) as 岸线长度,Sum(三生岸线.Shape_Length)/(SELECT Sum(三生岸线.Shape_Length) FROM 三生岸线)*100 &'%' as 比例\n" +
//                "FROM 三生岸线\n" +
//                "group by 岸线类型 ,市";
//        List<Map<String, Object>> select =
//                MdbfileUtils.select(filepath,sql, null);
//        for (Map<String, Object> stringObjectMap : select) {
//            System.out.println(stringObjectMap.toString());
//        }
//        return ResultUtil.success(select);
//    }

}
