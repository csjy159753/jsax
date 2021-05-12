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

    private static String filepath = null;
    /**
     * 获取mdb的地址
     * @return
     */

    public static  void getFilepath(){
        String dbPath = null;
        String filename="江苏省长江岸线库.mdb";                //读取要查找的文件名称filename;
        File dir=new File("\\\\192.168.1.8\\database");      //确定要查找的目录dir;
        boolean flag=false;                              //flag来确定是否查到文件;
        function(filename,dir,flag);                     //function函数递归实现;
    }

    /**
     * 实现查找文件是否存在
     * @param filename
     * @param dir
     * @param flag
     */
    public static void function(String filename,File dir,boolean flag)
    {

        File[]files=dir.listFiles();
        for(File file:files)
        {
            if(file.isDirectory())                      //file是目录时，则重新调用function函数;
            {
                function(filename,file.getAbsoluteFile(),flag);
            }
            if(file.isFile() && filename.equals(file.getName()))  //file时文件且文件名相同时，输出;
            {
                flag=true;
                System.out.println("要查找的文件路径为："+file.getAbsolutePath());
                filepath=file.getAbsolutePath();
                break;
            }
        }
        if(flag==true)                                 //判断有没有找到过文件;
            return;
        else
        {
            System.out.print(dir.getName()+"文件夹下没有找到此文件,去数据库中查找");
            filepath = "d:\\item\\1.mdb";
            return;
        }
    }



    @ApiOperation(value = "查询所有三生岸线有关信息")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Result<ListSub<CjaxCity>> list() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException {
        //String  filepath ="d:\\item\\1.mdb";
        getFilepath();
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
        getFilepath();
        List<Map<String, Object>> select =
                MdbfileUtils.select(filepath, "select DISTINCT 三生岸线.岸线类型 from 三生岸线" , null);
        for (Map<String, Object> stringObjectMap : select) {
            System.out.println(stringObjectMap.toString());
        }
        return ResultUtil.success(select);
    }

    @ApiOperation(value = "查询三生岸线类型及长度和比例")
    @RequestMapping(value = "lenAndPerForType", method = RequestMethod.GET)
    public Result<ListSub<CjaxCity>> lenAndPerForType() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException {
        //String  filepath ="d:\\item\\1.mdb";
        getFilepath();
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
