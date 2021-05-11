package com.jinhe.modules.City.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jinhe.common.util.ListSub;
import com.jinhe.common.util.Result;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.common.util.access.MdbfileUtils;
import com.jinhe.common.util.gemo.GeomJst;
import com.jinhe.modules.City.entity.CjaxCity;
import com.jinhe.modules.City.service.ICjaxCityService;
import com.jinhe.modules.system.entity.SysOperatorLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apiguardian.api.API;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rls
 * @since 2021-05-08
 */
@RestController
@RequestMapping("/City/cjax-city")
@Api(tags="axgntj")
public class CjaxCityController {

    @Autowired
    ICjaxCityService iCjaxCityService;


//    @ApiOperation(value = "查询所有市")
//    @RequestMapping(value = "list", method = RequestMethod.GET)
//    public Result<ListSub<CjaxCity>> list() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
//        QueryWrapper<CjaxCity> queryWrapper = new QueryWrapper();
//        queryWrapper.lambda().orderByAsc(CjaxCity::getId);
//        List<CjaxCity> list = iCjaxCityService.list(queryWrapper);
//        return ResultUtil.success(list);
//    }

//    public static void main(String[] args) throws SQLException {
//        String  filepath ="d:\\item\\1.mdb";
//        String table = "岸线功能区分区";
//        List<Map<String, Object>> select =
//                MdbfileUtils.select(filepath, "select  * from " + table, null);
//        for (Map<String, Object> stringObjectMap : select) {
//            System.out.println(stringObjectMap.toString());
//        }
//    }

    @ApiOperation(value = "查询所有信息")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Result<ListSub<CjaxCity>> list() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException {
        String  filepath ="d:\\item\\1.mdb";
        String table = "岸线功能区分区";
        List<Map<String, Object>> select =
                MdbfileUtils.select(filepath, "select  * from " + table, null);
        for (Map<String, Object> stringObjectMap : select) {
            System.out.println(stringObjectMap.toString());
        }
        return ResultUtil.success(select);
    }

    @ApiOperation(value = "岸线功能分划")
    @RequestMapping(value = "listForSideFunc", method = RequestMethod.GET)
    public Result<ListSub<CjaxCity>> listForSideFunc() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException {
        String  filepath ="d:\\item\\1.mdb";
        //String table = "岸线功能区分区";
        List<Map<String, Object>> select =
                MdbfileUtils.select(filepath, "SELECT 岸线功能区分区.[功能区类型]\n" +
                        "FROM 岸线功能区分区 group by 功能区类型 ", null);
        for (Map<String, Object> stringObjectMap : select) {
            System.out.println(stringObjectMap.toString());
        }
        return ResultUtil.success(select);
    }
    @ApiOperation(value = "各功能区类型占主江比例" )
    @RequestMapping(value = "proForFun", method = RequestMethod.GET)
    public Result<ListSub<CjaxCity>> proForFun() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException {
        String  filepath ="d:\\item\\1.mdb";
        //String table = "岸线功能区分区";
        List<Map<String, Object>> select =
                MdbfileUtils.select(filepath, "SELECT DISTINCT 岸线功能区分区.功能区类型,round(Sum(岸线功能区分区.Shape_Length)/(SELECT Sum(岸线功能区分区.Shape_Length) FROM 岸线功能区分区)*100 ,2) as 占主江比例\n" +
                        "FROM 岸线功能区分区\n" +
                        "GROUP BY 岸线功能区分区.功能区类型;", null);
        for (Map<String, Object> stringObjectMap : select) {
            System.out.println(stringObjectMap.toString());
        }
        return ResultUtil.success(select);
    }


    @ApiOperation(value = "分区岸线长度")
    @RequestMapping(value = "lengthForSide", method = RequestMethod.GET)
    public Result<ListSub<CjaxCity>> lengthForSide() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException {
        String  filepath ="d:\\item\\1.mdb";
        //String table = "岸线功能区分区";
        List<Map<String, Object>> select =
                MdbfileUtils.select(filepath, "SELECT DISTINCTROW 岸线功能区分区.功能区类型, Sum(岸线功能区分区.Shape_Length) AS [Shape_Length 之 合计]\n" +
                        "FROM 岸线功能区分区\n" +
                        "GROUP BY 岸线功能区分区.功能区类型;", null);
        for (Map<String, Object> stringObjectMap : select) {
            System.out.println(stringObjectMap.toString());
        }
        return ResultUtil.success(select);
    }

    @ApiOperation(value = "查询所有市")
    @RequestMapping(value = "listForCity", method = RequestMethod.GET)
    public Result<ListSub<Object>> listForCity() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException {
        String  filepath ="d:\\item\\1.mdb";
        String table = "市";
        List<Map<String, Object>> select =
                MdbfileUtils.select(filepath, "select  市,lon as 经度,lat as 纬度 from " + table, null);
        for (Map<String, Object> stringObjectMap : select) {
            System.out.println(stringObjectMap.toString());
        }
        return ResultUtil.success(select);
    }

    @ApiOperation(value = "查询所有水闸")
    @RequestMapping(value = "listForsluice", method = RequestMethod.GET)
    public Result<ListSub<CjaxCity>> listForsluice() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException {
        String  filepath ="d:\\item\\1.mdb";
        String table = "水闸";
        List<Map<String, Object>> select =
                MdbfileUtils.select(filepath, "select   * from " + table, null);
        for (Map<String, Object> stringObjectMap : select) {
            System.out.println(stringObjectMap.toString());
        }
        return ResultUtil.success(select);
    }

    @ApiOperation(value = "查询所有泵站")
    @RequestMapping(value = "listForBengZhan", method = RequestMethod.GET)
    public Result<ListSub<CjaxCity>> listForBengZhan() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException {
        String  filepath ="d:\\item\\1.mdb";
        String table = "泵站";
        List<Map<String, Object>> select =
                MdbfileUtils.select(filepath, "select   * from " + table, null);
        for (Map<String, Object> stringObjectMap : select) {
            System.out.println(stringObjectMap.toString());
        }
        return ResultUtil.success(select);
    }
    //框选
    @ApiOperation(value = "显示框选任意图形内的点")
    @RequestMapping(value = "pointInShapes", method = RequestMethod.GET)
    public Result<ListSub<CjaxCity>> pointInShapes(@RequestBody String xys) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException {
        String  filepath ="d:\\item\\1.mdb";
        String table = "岸线项目位置";
        //得到所有的经纬度
        List<Map<String, Object>> select =
                MdbfileUtils.select(filepath, "SELECT  岸线项目位置.lon as 经度, 岸线项目位置.lat AS 纬度\n" +
                        "FROM 岸线项目位置", null);
        //判断是否在多边形内
//       Boolean select2= GeomJst.polygonJudgment(xys,(Double)select.get(0)
//               .getOrDefault("经度",0),(Double)select.get(1).getOrDefault("纬度",0));
//
//        for (Map<String, Object> stringObjectMap : select) {
//            System.out.println(stringObjectMap.values());
//        }

        //输出的结果
        List<Map<String, Object>> res =new ArrayList<Map<String, Object>>();
        //写一个for循环，挨个判断，如果满足，就加进res
        for(Map<String, Object> stringObjectMap : select){
            Boolean infield= GeomJst.polygonJudgment(xys,(Double)select.get(0)
               .getOrDefault("经度",0),(Double)select.get(1).getOrDefault("纬度",0));
            if(infield){
                res.add(stringObjectMap);
            }
        }

        return ResultUtil.success(res);
    }

    //港口岸线利用率统计
    @ApiOperation(value = "港口岸线利用率统计")
    @RequestMapping(value = "statisticsForSide", method = RequestMethod.GET)
    public Result<ListSub<CjaxCity>> statisticsForSide() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException {
        String  filepath ="d:\\item\\1.mdb";
        String sql = "select DISTINCT 岸线功能区分区.所属市 as 城市名称 , Sum(岸线功能区分区.Shape_Length) as 岸线总长 , count(*) as 设施总数,Sum(岸线功能区分区.Shape_Length) as 利用总长 , " +
                "round(Sum(岸线功能区分区.Shape_Length)/(SELECT Sum(岸线功能区分区.Shape_Length) FROM 岸线功能区分区)*100,2)  as 岸线利用率 " +
                "from 岸线功能区分区  " +
                "where 岸线功能区分区.所属市 in (select  市 from 市 ) " +
                "group by  岸线功能区分区.所属市";
        List<Map<String, Object>> select =
                MdbfileUtils.select(filepath, sql, null);
        for (Map<String, Object> stringObjectMap : select) {
            System.out.println(stringObjectMap.toString());
        }
        return ResultUtil.success(select);
    }


    @ApiOperation(value = "港口利用率统计")
    @RequestMapping(value = "statisticsForPort", method = RequestMethod.GET)
    public Result<ListSub<CjaxCity>> statisticsForPort() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException {
        String  filepath ="d:\\item\\1.mdb";
        String sql = "select DISTINCT 港区.名称 as 港口名称 ,Sum(港区.岸线长度) as 岸线长度,\n" +
                "round(Sum(港区.岸线长度)/(SELECT Sum(港区.岸线长度) FROM 港区)*100,2)  as 比例\n" +
                "from 港区\n" +
                "group by 港区.名称";
        List<Map<String, Object>> select =
                MdbfileUtils.select(filepath, sql, null);
        for (Map<String, Object> stringObjectMap : select) {
            System.out.println(stringObjectMap.toString());
        }
        return ResultUtil.success(select);
    }


}

