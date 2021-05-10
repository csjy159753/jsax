package com.jinhe.common.util.access;

import java.lang.reflect.Field;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;


/**
 * @program: javalearning
 * @Date: 2018/7/11 13:56
 * @Author: hyman.hu
 * @Description: DB工具类
 */
public class MdbfileUtils {
    public static final SimpleDateFormat YMD = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 增加、删除、改
     *
     * @param sql    sql
     * @param params 参数
     * @return 添加结果
     */
    public static boolean update(String filepath, String sql, List<Object> params) throws SQLException {
        int result = -1;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = AccessDBUtils.getConn(filepath);
            assert conn != null;//直接抛异常
            ps = conn.prepareStatement(sql);
            int index = 1;
            if (params != null && !params.isEmpty()) {
                for (Object param : params) {
                    ps.setObject(index++, param);
                }
            }
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                assert conn != null;
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw e;
        } finally {
            AccessDBUtils.close(conn, ps, null);
        }
        return result > 0;
    }

    /**
     * 查询多条记录
     *
     * @param sql    sql
     * @param params 参数&
     * @return 查询结果
     */
    public static List<Map<String, Object>> select(String filepath, String sql, List<Object> params) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        int index = 1;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = AccessDBUtils.getConn(filepath);
            assert conn != null;
            ps = conn.prepareStatement(sql);
            if (params != null && !params.isEmpty()) {
                for (Object param : params) {
                    ps.setObject(index++, param);
                }
            }
            rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int col_len = metaData.getColumnCount();
            List<String> colnames = new ArrayList<String>();
            for (int i = 0; i < col_len; i++) {
                colnames.add(metaData.getColumnName(i + 1));
            }

//            System.out.println("col_len="+col_len);
            Map<String, Object> map = null;
            while (rs.next()) {
                map = new HashMap<String, Object>();
                for (int i = 0; i < col_len; i++) {
                    String cols_name = colnames.get(i);//metaData.getColumnName(i + 1);
                    //Object cols_value = rs.getObject(cols_name);
                    Object cols_value = rs.getObject(i+1);
                    if (cols_value == null) {
                        cols_value = "";
                    }
                    map.put(cols_name, cols_value);
                }
                list.add(map);
//                System.out.println(list.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            AccessDBUtils.close(conn, ps, rs);
        }
        return list;
    }

    /**
     * 通过反射机制查询多条记录
     *
     * @param sql    sql
     * @param params 参数
     * @param clazz  类
     * @return 查询结果
     */
    public static <T> List<T> select(String filepath, String sql, List<Object> params,
                                     Class<T> clazz) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<T> list = new ArrayList<>();
        int index = 1;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = AccessDBUtils.getConn(filepath);
            assert conn != null;
            ps = conn.prepareStatement(sql);
            if (params != null && !params.isEmpty()) {
                for (Object param : params) {
                    ps.setObject(index++, param);
                }
            }
            rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int cols_len = metaData.getColumnCount();
            T t;
            while (rs.next()) {
                //通过反射机制创建一个实例
                t = clazz.newInstance();
                for (int i = 0; i < cols_len; i++) {
                    String cols_name = metaData.getColumnName(i + 1);
                    Object cols_value = rs.getObject(cols_name);
                    if (cols_value == null) {
                        cols_value = "";
                    }
                    Field field = clazz.getDeclaredField(cols_name);//获取对象属性
                    field.setAccessible(true); //打开javabean的访问权限
                    field.set(t, cols_value);
                }
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            AccessDBUtils.close(conn, ps, rs);
        }
        return list;
    }

    //返回mdb库里所有的表名，因为实际上我也不知道表名是不是固定的，很有可能不是。但ss库的表名应该是固定的，所以我只能遍历mdb表名，再和ss库依次比对，再插入数据
    public static List<String> getAllTables(String filepath) throws SQLException {
        List<String> tables = new ArrayList<>();
        int index = 1;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = AccessDBUtils.getConn(filepath);
            Statement stmt=conn.createStatement();
            DatabaseMetaData dbmd=conn.getMetaData();
            rs = dbmd.getTables(null,null,"%",null);
            while(rs.next()){
                tables.add(rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            AccessDBUtils.close(conn, ps, rs);
        }

        return tables;
    }

    //获取mdb库中某种表的主键列表
    public static List<String>  getTablekeyFileds(String filepath, String tablename) throws SQLException {
        List<String> keys = new ArrayList<>();
        int index = 1;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = AccessDBUtils.getConn(filepath);
            Statement stmt=conn.createStatement();
            DatabaseMetaData dbmd=conn.getMetaData();
            rs = dbmd.getPrimaryKeys(null,null, tablename);
            while(rs.next()){
                keys.add(rs.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            AccessDBUtils.close(conn, ps, rs);
        }

        return keys;
    }

    public static String fmtTime(Date d){

        if (d ==null){
            return "";
        }

        return YMD.format(d);
    }
}