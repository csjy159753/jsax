package com.jinhe.common.exception;

import com.jinhe.common.util.Result;
import com.jinhe.common.util.ResultUtil;
import com.jinhe.common.config.SystemResultEnum;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 */
@RestControllerAdvice
public class RRExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(RRException.class)
    public Result handleRRException(RRException e) {
        ResultUtil.error(e.getCode(), e.getMessage());
        return ResultUtil.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        logger.error(e.getMessage(), e);
        return ResultUtil.Info(SystemResultEnum.DUPLICATE_KEY);
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return ResultUtil.error();
    }

    @ExceptionHandler(value = {SignatureException.class})
    @ResponseBody
    public Result authorizationException(SignatureException e) {

        return ResultUtil.Info(SystemResultEnum.TOKRN_ERROR);
    }

    @ExceptionHandler(value = {DuplicateSubmitException.class})
    @ResponseBody
    public Result duplicateSubmitException(DuplicateSubmitException e) {

        return ResultUtil.Info(SystemResultEnum.DUPLICATE_SUBMIT);
    }
}
