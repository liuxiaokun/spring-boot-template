package com.cloudoer.project.project.module.dao;

import com.cloudoer.project.project.module.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/9/4
 */
@Mapper
public interface UserMapper {

    List<User> query();
}
