package com.cloudoer.project.project.module.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/9/7
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    private static final String roleQuery =
            "select u.name, r.role_name from user u inner join user_role ur on ur.user_id = u.id inner join role r on r.id=ur.role_id" +
                    " where u.name = ?";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        /*auth.inMemoryAuthentication().passwordEncoder(passwordEncoder).
                withUser("fred").password(passwordEncoder.encode("fred"))
                .roles("ADMIN","USER");*/

        auth.jdbcAuthentication().passwordEncoder(passwordEncoder)
                .dataSource(dataSource).usersByUsernameQuery("select name, password, enable from user where name = ?")
                .authoritiesByUsernameQuery(roleQuery);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().anyRequest().authenticated()
                .and().formLogin().and().httpBasic();
    }
}
