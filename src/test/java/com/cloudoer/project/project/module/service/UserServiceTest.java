package com.cloudoer.project.project.module.service;

import com.cloudoer.project.project.module.dto.UserDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/9/5
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testAddUser() {

        UserDto userDto = new UserDto();
        userDto.setName("test add");
        userService.addUser(userDto);
    }

    @Test
    public void testGetById() {

        Long userId = 6L;

        UserDto userDto = userService.getUserById(userId);
        Assert.assertNotNull(userDto);

        UserDto userDto1 = userService.getUserById(userId);
        Assert.assertNotNull(userDto);

    }
}
