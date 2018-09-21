package com.cloudoer.project.project.module.controller.comm;

import com.cloudoer.project.project.module.consts.RespCode;
import com.cloudoer.project.project.module.consts.RespMsg;
import com.cloudoer.project.project.module.utils.RespUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/9/21
 */
@RestController
public class GlobalErrorController {


    @RequestMapping("/error/404")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object handling() {

        return RespUtil.fail(RespCode.NOT_FOUND, RespMsg.NOT_FOUND);
    }
}
