package com.jinhe.config;


import io.jsonwebtoken.SignatureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class PermissionHandler {
    @ExceptionHandler(value = { SignatureException.class })
    @ResponseBody
    public res<?> authorizationException(SignatureException e){
        return ResultTool.error(new ExceptionInfoBO(1008,e.getMessage()));
    }
}