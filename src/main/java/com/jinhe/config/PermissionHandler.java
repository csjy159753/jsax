package com.jinhe.config;


import com.jinhe.common.util.ResultEnum;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.common.vo.Result;
import io.jsonwebtoken.SignatureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class PermissionHandler {
    @ExceptionHandler(value = { SignatureException.class })
    @ResponseBody
    public Result authorizationException(SignatureException e){
        return ResultUtil.error(ResultEnum.UNKNOWN_ERROR.getCode(),e.getMessage());
    }
}