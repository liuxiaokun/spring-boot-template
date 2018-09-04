package com.cloudoer.project.project.module.controller;

import com.cloudoer.project.project.module.config.UserConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/9/4
 */
@RestController
@Slf4j
public class TestController {

    @Autowired
    private UserConfig userConfig;

    @GetMapping
    public Object test() {
        return "hello spring boot, " + new Date();
    }

    @GetMapping("config")
    public Object testConfig() {
        log.error(userConfig.toString());
        return userConfig;
    }

}
