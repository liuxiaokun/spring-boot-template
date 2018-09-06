package com.cloudoer.project.project.module.exception;

import com.cloudoer.project.project.module.utils.RespUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.cloudoer.project.project.module.consts.RespCode.SERVER_ERROR;
import static com.cloudoer.project.project.module.consts.RespMsg.SERVER_INTERNAL_ERROR;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/9/6
 */
@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        e.printStackTrace();
        return RespUtil.fail(SERVER_ERROR, SERVER_INTERNAL_ERROR);
    }
}
