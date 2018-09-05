package com.cloudoer.project.project.module.service;

import com.cloudoer.project.project.module.dto.UserDto;
import com.github.pagehelper.PageInfo;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/9/4
 */
public interface UserService {


    PageInfo<UserDto> list();

    void addUser(UserDto userDto);

    UserDto getUserById(Long userId);
}
