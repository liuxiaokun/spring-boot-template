package com.cloudoer.project.project.module.service.impl;

import com.cloudoer.project.project.module.bean.User;
import com.cloudoer.project.project.module.dao.UserMapper;
import com.cloudoer.project.project.module.service.UserService;
import com.cloudoer.project.project.module.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<UserVo> list() {

        List<User> query = userMapper.query();

        List<UserVo> vos = new ArrayList<>();
        query.forEach(v -> {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(v, userVo);
            vos.add(userVo);
        });
        return vos;
    }
}
