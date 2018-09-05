package com.cloudoer.project.project.module.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/9/4
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseBean implements Serializable {

    private String name;
}
