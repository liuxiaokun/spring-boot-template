package com.cloudoer.project.project.module.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/9/21
 */
@Component
public class ErrorPageConfig implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage[] errorPages = new ErrorPage[]{
                new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"),
        };
        registry.addErrorPages(errorPages);
    }
}