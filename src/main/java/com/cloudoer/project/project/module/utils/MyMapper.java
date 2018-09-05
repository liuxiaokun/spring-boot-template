package com.cloudoer.project.project.module.utils;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/9/5
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
