package com.cloudoer.project.project.module.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/9/4
 */
@Setter
@Getter
@ToString
@ConfigurationProperties(prefix = "user")
@Component
public class UserConfig {

    private String cname;
    private Integer age;
    private String info;
}
