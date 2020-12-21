package com.jinhe.common.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jinhe.common.config.ResultEnum;

import java.util.List;

/**
 * @author Administrator
 */
public class ResultUtil {

    /**
     * 成功且带数据
     **/
    public static <T> Result<ListSub<T>> success(IPage<T> page) {
        if (page == null) {
            return success();
        }
        ListSub<T> listSub = new ListSub();
        listSub.setList(page.getRecords());
        listSub.setTotal(page.getTotal());
        Result<ListSub<T>> result = new Result<>();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(listSub);
        return result;
    }

    /**
     * 成功且带数据
     **/
    public static <T> Result<ListSub<T>> success(List<T> list, Long total) {
        ListSub<T> listSub = new ListSub();
        listSub.setList(list);
        listSub.setTotal(total);
        Result<ListSub<T>> result = new Result<>();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(listSub);
        return result;
    }

    /**
     * 成功且带数据
     **/
    public static <T> Result success(T t) {
        Result<T> result = new Result<>();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(t);
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
    public static <T extends ResultEnum> Result Info(T resultEnum) {
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return result;
    }

    /**
     * 默认错误返回
     **/
    public static <T extends ResultEnum> Result error(T resultEnum) {
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return result;
    }
}
