package com.cloudoer.project.project.module.service;

import com.cloudoer.project.project.module.dto.UserDto;

import java.util.List;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/9/4
 */
public interface UserService {


    List<UserDto> list();

    void addUser(UserDto userDto);
}
