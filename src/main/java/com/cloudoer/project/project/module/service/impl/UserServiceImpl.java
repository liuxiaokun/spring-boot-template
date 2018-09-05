package com.cloudoer.project.project.module.service.impl;

import com.cloudoer.project.project.module.bean.User;
import com.cloudoer.project.project.module.dao.UserMapper;
import com.cloudoer.project.project.module.dto.UserDto;
import com.cloudoer.project.project.module.service.UserService;
import com.cloudoer.project.project.module.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/9/4
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDto> list() {

        List<User> query = userMapper.query();

        List<UserDto> vos = new ArrayList<>();
        query.forEach(v -> {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(v, userDto);
            vos.add(userDto);
        });
        return vos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        userMapper.add(user);
        user.setName("test transaction1");
        userMapper.add(user);
        user.setName("test transaction2 to long varchar string string string string string string ");
        userMapper.add(user);
        user.setName("test transaction3");
        userMapper.add(user);
    }
}
