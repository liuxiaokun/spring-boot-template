package com.cloudoer.project.project.module.controller;

import com.cloudoer.project.project.module.bean.User;
import com.cloudoer.project.project.module.config.UserConfig;
import com.cloudoer.project.project.module.dto.UserDto;
import com.cloudoer.project.project.module.service.UserService;
import com.cloudoer.project.project.module.utils.RespUtil;
import com.cloudoer.project.project.module.vo.UserVo;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static com.cloudoer.project.project.module.consts.Constant.DEFAULT_LOCK_VERSION;
import static com.cloudoer.project.project.module.consts.RespCode.SUCCESS;
import static com.cloudoer.project.project.module.consts.RespMsg.OPERATION_SUCCESS;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/9/4
 */
@RestController
@Slf4j
public class TestController {

    @Autowired
    private UserConfig userConfig;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserService userService;

    @GetMapping
    public Object test() {
        return "hello spring boot, " + new Date();
    }

    @GetMapping("config")
    public Object testConfig() {
        log.error(userConfig.toString());
        return userConfig;
    }

    @GetMapping("redis")
    public Object testRedis(String key) {
        log.info("key:{}", key);
        log.info("get value:{}", stringRedisTemplate.opsForValue().get(key));
        stringRedisTemplate.opsForValue().set(key, UUID.randomUUID().toString());
        log.info("get value:{}", stringRedisTemplate.opsForValue().get(key));
        return key;
    }

    @GetMapping("jdbc")
    public Object testJdbc() {
        log.info("jdbc....");
        return jdbcTemplate.queryForObject("select count(*) from USER", Integer.class);
    }

    @GetMapping("mybatis")
    public Object testMybatis(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return RespUtil.succeed(SUCCESS, OPERATION_SUCCESS, userService.list());
    }

    @PostMapping("user")
    public Object testMybatisAdd(UserVo userVo) {

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userVo, userDto);
        userService.addUser(userDto);
        return "SUCCESS";
    }

    @GetMapping("ng")
    public Object testNg(HttpServletRequest request) {
        log.info("ng, port:{}", request.getLocalPort());
        return "ng:" + request.getLocalPort();
    }

    @GetMapping("session")
    public Object testSession(HttpServletRequest request) {
        log.info("session, port:{}", request.getLocalPort());

        HttpSession session = request.getSession();
        log.info("session class:{}", session.getClass());
        log.info("session id:{}", session.getId());

        log.info("session value before:{}", session.getAttribute("user"));
        session.setAttribute("user", "fred");
        log.info("session value after:{}", session.getAttribute("user"));
        return "hey, fred. :" + request.getLocalPort();
    }

    @GetMapping("mapper")
    public Object testCommonMapper(Long userId) {
        return RespUtil.succeed(SUCCESS, OPERATION_SUCCESS, userService.getUserById(userId));
    }

    @PostMapping("mapper")
    public Object testCommonMapperAdd(UserVo userVo) {

        User user = new User();
        Optional.ofNullable(userVo).ifPresent(vo -> {
            BeanUtils.copyProperties(userVo, user);
            user.setVersion(DEFAULT_LOCK_VERSION);
        });
        return userService.save(user);
    }

    @GetMapping("retry")
    @Retryable(value = IllegalArgumentException.class, maxAttempts = 5, backoff = @Backoff(delay = 1000, multiplier = 2))
    public void testRetry() {

        log.info("test retry---------------");
        throw new IllegalArgumentException();

    }
}
