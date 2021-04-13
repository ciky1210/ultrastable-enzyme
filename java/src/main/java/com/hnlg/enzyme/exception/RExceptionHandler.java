package com.hnlg.enzyme.exception;


import com.hnlg.enzyme.enums.ExceptionEnum;
import com.hnlg.enzyme.util.ResultInfo;
import org.apache.logging.log4j.core.config.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * 全局方法异常处理器
 */
@Order(-1)
@RestControllerAdvice
public class RExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 日志保存异常信息
     *
     * @param e
     */
    private void logErr(Exception e) {
        logger.error(e.getMessage(), e);
    }

    /**
     * 全局异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResultInfo handleException(Exception e) {
        logErr(e);
        e.printStackTrace();
        return ResultInfo.error(ExceptionEnum.UNKNOW_ERROR);
    }

    /**
     * 自定义异常
     */
    @ExceptionHandler(RException.class)
    public ResultInfo handleRRException(RException e) {
        ResultInfo r = new ResultInfo();
        r.setCode(e.getCode());
        r.setMsg(e.getMsg());
        r.setData("");
        return r;
    }

    /**
     * 请求方法异常
     *
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultInfo handleMethodNotException(HttpRequestMethodNotSupportedException e) {
        logErr(e);
        e.printStackTrace();
        return ResultInfo.error(ExceptionEnum.HTTP_METHOD_NOT_SUPPORT);
    }

    /**
     * 参数校验失败的异常
     *
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultInfo handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logErr(e);
        e.printStackTrace();
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        StringBuilder stringBuffer = new StringBuilder();
        for (ObjectError allError : allErrors) {
            stringBuffer.append(allError.getDefaultMessage()).append(";");

        }
        return ResultInfo.error(-100, stringBuffer.toString());
    }

    /**
     * http body参数无法读取转换
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultInfo handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logErr(e);
        e.printStackTrace();
        return ResultInfo.error(ExceptionEnum.HTTP_BODY_NOT_READABLE);
    }

    /**
     * mysql语法错误
     *
     * @return
     */
    @ExceptionHandler(BadSqlGrammarException.class)
    public ResultInfo handleBadSqlGrammarException(BadSqlGrammarException e) {
        logErr(e);
        e.printStackTrace();
        return ResultInfo.error(ExceptionEnum.MYSQL_SQL_GRAMMAR_ERROR);
    }

    /**
     * 服务器参数异常，一般是因为少传了参数导致
     *
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultInfo handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        logErr(e);
        e.printStackTrace();
        return ResultInfo.error(ExceptionEnum.HTTP_PARAMETER_ERROR);
    }

    /**
     * 方法级别的参数校验异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultInfo handleConstraintViolationException(ConstraintViolationException e) {
        logErr(e);
        e.printStackTrace();
        StringBuilder eMsg = new StringBuilder();
        for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
            eMsg.append(constraintViolation.getMessage()).append(";");
        }
        return ResultInfo.error(-100, eMsg.toString());
    }

    /**
     * 绑定的方法参数异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ResultInfo handleBindException(BindException e) {
        logErr(e);
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        StringBuilder stringBuffer = new StringBuilder();
        for (ObjectError allError : allErrors) {
            stringBuffer.append(allError.getDefaultMessage()).append(";");
        }
        return ResultInfo.error(-100, stringBuffer.toString());
    }

    /**
     * 方法参数无法转换异常，通常为定义一个Bol 类型的值，传过来的参数无法转换为bol  如null string int>2
     *
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResultInfo handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        logErr(e);
        e.printStackTrace();
        return ResultInfo.error(ExceptionEnum.METHOD_ARGUMENT_TYPE_MISMATCH_ERROR);
    }
}

