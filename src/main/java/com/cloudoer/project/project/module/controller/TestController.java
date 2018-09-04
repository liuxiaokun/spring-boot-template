package com.cloudoer.project.project.module.controller;

import com.cloudoer.project.project.module.config.UserConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

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

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping
    public Object test() {
        return "hello spring boot, " + new Date();
    }

    @GetMapping("config")
    public Object testConfig() {
        log.error(userConfig.toString());
        return userConfig;
    }

    @GetMapping("redis")
    public Object testRedis(String key) {
        log.info("key:{}", key);
        log.info("get value:{}",stringRedisTemplate.opsForValue().get(key));
        stringRedisTemplate.opsForValue().set(key, UUID.randomUUID().toString());
        log.info("get value:{}",stringRedisTemplate.opsForValue().get(key));
        return key;
    }

}
