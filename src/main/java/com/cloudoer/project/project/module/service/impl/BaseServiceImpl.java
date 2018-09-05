package com.cloudoer.project.project.module.service.impl;

import com.cloudoer.project.project.module.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/9/5
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    protected Mapper<T> mapper;

    @Override
    public T getOne(T t) {
        return mapper.selectOne(t);
    }

    @Override
    public int save(T entity) {
        return mapper.insert(entity);
    }

    @Override
    public int delete(T entity) {
        return mapper.deleteByPrimaryKey(entity);
    }

    @Override
    public T getById(Object id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<T> getAll() {
        return this.mapper.selectAll();
    }
}
