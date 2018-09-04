package com.cloudoer.project.project.module.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/9/4
 */
@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource(Environment env) {

        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        druidDataSource.setUrl(env.getProperty("spring.datasource.url"));
        druidDataSource.setUsername(env.getProperty("spring.datasource.username"));
        druidDataSource.setPassword(env.getProperty("spring.datasource.password"));
        druidDataSource.setInitialSize(2);
        druidDataSource.setMaxActive(160);
        druidDataSource.setMinIdle(0);
        druidDataSource.setMaxWait(60000);

        return druidDataSource;
    }
}
