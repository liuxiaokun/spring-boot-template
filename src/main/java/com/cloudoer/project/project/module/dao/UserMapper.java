package com.cloudoer.project.project.module.dao;

import com.cloudoer.project.project.module.bean.User;
import com.cloudoer.project.project.module.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/9/4
 */
@Mapper
@Component
public interface UserMapper extends MyMapper<User> {

    /**
     * query all data from user table
     * @return all users
     */
    List<User> query();

    /**
     * insert one user into user table
     * @param user data will be inserted.
     */
    void add(User user);

    User queryByName(String name);
}
