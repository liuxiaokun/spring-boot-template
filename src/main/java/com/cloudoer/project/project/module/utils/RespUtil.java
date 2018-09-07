package com.cloudoer.project.project.module.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import static com.cloudoer.project.project.module.consts.Constant.EMPTY;
import static com.cloudoer.project.project.module.consts.RespCode.FAILURE;
import static com.cloudoer.project.project.module.consts.RespCode.SUCCESS;
import static com.cloudoer.project.project.module.consts.RespMsg.*;

/**
 * 用于Restful webService API数据接口统一返回。
 *
 * @author liuxiaokun
 * @version 0.0.1
 * @see com.cloudoer.project.project.module.consts.RespCode 返回码常量
 * @see com.cloudoer.project.project.module.consts.RespMsg 返回信息常量
 * @since 2018/9/6
 */
@AllArgsConstructor
@Data
public class RespUtil {

    /**
     * 返回码常量
     *
     * @see com.cloudoer.project.project.module.consts.RespCode 返回码常量
     */
    private int code;

    /**
     * 返回信息常量
     *
     * @see com.cloudoer.project.project.module.consts.RespMsg 返回信息常量
     */
    private String message;

    /**
     * 返回数据 Vo, 可以为任意数据类型
     */
    private Object data;

    /**
     * 数据接口访问 <b>成功</b> 的统一返回格式
     * <ul>
     * <li>code为 <code>SUCCESS</code> </li>
     * <li>message为 <code>OPERATION_SUCCESS<code/> </li>
     * <li>返回空数据 <code>EMPTY</code> </li>
     * </ul>
     *
     * @return 返回实体
     */
    public static Object succeed() {
        return new RespUtil(SUCCESS, OPERATION_SUCCESS, EMPTY);
    }

    /**
     * 数据接口访问 <b>成功</b> 的统一返回格式
     * <ul>
     * <li>code为 <code>SUCCESS</code></li>
     * <li>返回空数据 <code>EMPTY</code></li>
     * </ul>
     *
     * @param message 返回提示信息
     * @return 返回实体
     */
    public static Object succeed(String message) {
        return new RespUtil(SUCCESS, message, EMPTY);
    }

    /**
     * 数据接口访问 <b>成功</b> 的统一返回格式
     * <ul>
     * <li>code为 <code>SUCCESS</code> </li>
     * <li>message为 <code>OPERATION_SUCCESS<code/> </li>
     * </ul>
     *
     * @param data 返回数据
     * @return 返回实体
     */
    public static Object succeed(Object data) {
        return new RespUtil(SUCCESS, OPERATION_SUCCESS, data);
    }

    /**
     * 数据接口访问 <b>成功</b> 的统一返回格式
     * <ul>
     * <li>code为 <code>SUCCESS</code> </li>
     * </ul>
     *
     * @param message 返回提示信息
     * @param data    返回数据
     * @return 返回实体
     */
    public static Object succeed(String message, Object data) {
        return new RespUtil(SUCCESS, message, data);
    }

    /**
     * 数据接口访问 <b>成功</b> 的统一返回格式
     *
     * @param code    返回码
     * @param message 返回提示信息
     * @param data    返回数据
     * @return 返回实体
     */
    public static Object succeed(int code, String message, Object data) {
        return new RespUtil(code, message, data);
    }

    /**
     * 数据接口访问 <b>失败</b> 的统一返回格式
     * <ul>
     * <li>code为 <code>FAILURE</code></li>
     * <li>message为 <code>SERVER_INTERNAL_ERROR </code></li>
     * <li>返回空数据</li>
     * </ul>
     *
     * @return 返回实体
     */
    public static Object fail() {
        return new RespUtil(FAILURE, SERVER_INTERNAL_ERROR, EMPTY);
    }

    /**
     * 数据接口访问 <b>失败</b> 的统一返回格式
     * <ul>
     * <li>message为 <code>SERVER_INTERNAL_ERROR </code></li>
     * <li>返回空数据</li>
     * </ul>
     *
     * @param code 返回码
     * @return 返回实体
     */
    public static Object fail(int code) {
        return new RespUtil(code, OPERATION_FAILURE, EMPTY);
    }

    /**
     * 数据接口访问 <b>失败</b> 的统一返回格式
     * <ul>
     * <li>返回空数据</li>
     * </ul>
     *
     * @param code    返回码
     * @param message 返回提示信息
     * @return 返回实体
     */
    public static Object fail(int code, String message) {
        return new RespUtil(code, message, EMPTY);
    }

    /**
     * 数据接口访问 <b>失败</b> 的统一返回格式
     *
     * @param code    返回码
     * @param message 返回提示信息
     * @param data    返回数据
     * @return 返回实体
     */
    public static Object fail(int code, String message, Object data) {
        return new RespUtil(code, message, data);
    }

}
