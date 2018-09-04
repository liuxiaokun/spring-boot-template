package com.cloudoer.project.project.module.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/9/4
 */
@RestController
public class TestController {

    @GetMapping
    public Object test() {
        return "hello spring boot, " + new Date();
    }

}
