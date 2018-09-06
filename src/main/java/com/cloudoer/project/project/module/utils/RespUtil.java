package com.cloudoer.project.project.module.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author liuxiaokun
 * @version 0.0.1
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

    public static Object fail(String code, String message, Object data) {
        return new RespUtil(false, code, message, data);
    }
}
