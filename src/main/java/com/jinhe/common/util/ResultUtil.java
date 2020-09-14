package com.jinhe.common.util;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.HashMap;
import java.util.List;

public class ResultUtil {
    /**
     * 成功且带数据
     **/
    public static <T> Result success(IPage<T> page) {
        if (page == null) {
            success();
        }
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("list", page.getRecords());
        hashMap.put("total", page.getTotal());
        result.setData(hashMap);
        return result;
    }

    /**
     * 成功且带数据
     **/
    public static <T> Result success(List<T> list, Long total) {
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("list", list);
        hashMap.put("total", total);
        result.setData(hashMap);
        return result;
    }

    /**
     * 成功且带数据
     **/
    public static <T> Result success(List<T> list, Long total, boolean tree) {
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("list", list);
        hashMap.put("total", total);
        hashMap.put("tree", tree);
        result.setData(hashMap);
        return result;
    }

    /**
     * 成功且带数据
     **/
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    /**
     * 成功但不带数据
     **/
    public static Result success() {
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        return result;
    }

    /**
     * 成功但不带数据
     **/
    public static Result success(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * 失败
     **/
    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * 默认未知错误
     **/
    public static Result error(Exception ex) {
        Result result = new Result();
        result.setCode(ResultEnum.UNKNOWN_ERROR.getCode());
        result.setMsg(ex.getMessage());
        return result;
    }
    /**
     * 默认未知错误
     **/
    public static Result error() {
        Result result = new Result();
        result.setCode(ResultEnum.UNKNOWN_ERROR.getCode());
        result.setMsg(ResultEnum.UNKNOWN_ERROR.getMsg());
        return result;
    }

    /**
     * 默认info返回
     **/
    public static Result Info(ResultEnum resultEnum) {
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return result;
    }

    /**
     * 默认错误返回
     **/
    public static Result error(ResultEnum resultEnum) {
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return result;
    }
}
