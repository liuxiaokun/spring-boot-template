package com.cloudoer.project.project.module.service;

import java.util.List;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/9/5
 */
public interface BaseService<T> {

    T getOne(T t);

    int save(T entity);

    int delete(T entity);

    T getById(Object id);

    List<T> getAll();
}
