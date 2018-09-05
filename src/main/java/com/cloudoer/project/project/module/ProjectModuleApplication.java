package com.cloudoer.project.project.module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/9/4
 */
@SpringBootApplication
@EnableCaching
public class ProjectModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectModuleApplication.class, args);
    }
}
