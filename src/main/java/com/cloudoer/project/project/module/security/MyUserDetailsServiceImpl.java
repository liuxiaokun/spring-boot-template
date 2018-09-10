package com.cloudoer.project.project.module.security;

import com.cloudoer.project.project.module.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/9/7
 */
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return null;
    }
}
