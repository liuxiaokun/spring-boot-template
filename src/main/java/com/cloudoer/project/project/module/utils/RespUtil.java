package com.cloudoer.project.project.module.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.cloudoer.project.project.module.consts.Constant.EMPTY;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @see com.cloudoer.project.project.module.consts.RespCode
 * @see com.cloudoer.project.project.module.consts.RespMsg
 * @since 2018/9/6
 */
@AllArgsConstructor
@Data
public class RespUtil {

    private Boolean isSuccess;
    private String code;
    private String message;
    private Object data;

    public static Object succeed(String code, String message, Object data) {
        return new RespUtil(true, code, message, data);
    }

    public static Object succeed(String code, String message) {
        return new RespUtil(true, code, message, EMPTY);
    }

    public static Object fail(String code, String message, Object data) {
        return new RespUtil(false, code, message, data);
    }

    public static Object fail(String code, String message) {
        return new RespUtil(false, code, message, EMPTY);
    }
}
