package com.cloudoer.project.project.module.exception;

import com.cloudoer.project.project.module.utils.RespUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.cloudoer.project.project.module.consts.RespCode.EXCEPTION;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/9/6
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        log.error("global handle Exception", e);
        return RespUtil.fail();
    }

    @ExceptionHandler(BizException.class)
    public Object bizExceptionHandler(BizException ex) {
        log.error("runtimeExceptionHandler", ex);
        return RespUtil.fail(EXCEPTION, ex.getMessage());
    }

}
