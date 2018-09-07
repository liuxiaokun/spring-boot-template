package com.cloudoer.project.project.module.consts;

/**
 * <p>对响应码做如下约定</p>
 * <ul>
 * <li>100XX 为内置基本响应码</li>
 * <li>200XX 为内置预留响应码</li>
 * <li>300XX 为内置预留响应码</li>
 * <li>400XX 为内置预留响应码</li>
 * <li>500XX 为内置预留响应码</li>
 * <li>600XX 为自定义响应码
 * </ul>
 *
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/9/6
 */
public interface RespCode {

    int SUCCESS = 10000;

    int NOT_SIGNIN = 40000;
    int FORBIDDEN = 40003;
    int NOT_FOUND = 40004;

    int FAILURE = 50000;
    int EXCEPTION = 50001;

}
