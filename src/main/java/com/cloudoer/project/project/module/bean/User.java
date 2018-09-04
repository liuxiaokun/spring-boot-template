package com.cloudoer.project.project.module.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/9/4
 */
@Data
public class User implements Serializable {

    private Long id;

    private String name;
}
