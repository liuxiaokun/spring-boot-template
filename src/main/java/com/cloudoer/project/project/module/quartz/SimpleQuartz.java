package com.cloudoer.project.project.module.quartz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/9/6
 */
@Component
@Configurable
@EnableScheduling
@Slf4j
public class SimpleQuartz {


    @Scheduled(cron = "0 0/1 * * * ?")
    public void report() {
        log.info("定时器运行了");
    }
}
