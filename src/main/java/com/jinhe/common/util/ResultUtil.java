package com.jinhe.common.util;

import com.jinhe.common.vo.Result;
import lombok.Data;

import java.util.List;

public class ResultUtil {
    /**成功且带数据**/
    public static<T> Result success(List<T> list,Long total){
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        ListSub<T> listSub=new ListSub<>();
        listSub.setList(list);
        listSub.setTotal(total);
        result.setData(listSub);
        return result;
    }
    /**成功且带数据**/
    public static Result success(Object object){
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }
    /**成功但不带数据**/
    public static Result success(){
        return success(null);
    }

    /**失败**/
    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
    /**默认未知错误**/
    public static Result error(){
        Result result = new Result();
        result.setCode(ResultEnum.UNKNOWN_ERROR.getCode());
        result.setMsg(ResultEnum.UNKNOWN_ERROR.getMsg());
        return result;
    }
    /**默认未知错误**/
    public static Result error(ResultEnum resultEnum){
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return result;
    }
}
