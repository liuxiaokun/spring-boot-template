package com.cloudoer.project.project.module.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/9/7
 */
@Configuration
public class AsyncConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {

        ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
        threadPoolExecutor.setCorePoolSize(5);
        threadPoolExecutor.setMaxPoolSize(50);
        threadPoolExecutor.setQueueCapacity(100);

        threadPoolExecutor.initialize();

        return threadPoolExecutor;
    }
}
