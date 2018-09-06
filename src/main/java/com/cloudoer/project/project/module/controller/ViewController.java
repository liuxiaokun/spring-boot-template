package com.cloudoer.project.project.module.controller;

import com.cloudoer.project.project.module.vo.ThymeleafVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/9/6
 */
@Controller
@RequestMapping("/books")
public class ViewController {


    @GetMapping("/")
    public ModelAndView index(){
        List<ThymeleafVo> voList =new ArrayList<>();
        ThymeleafVo thymeleafVo =new ThymeleafVo("官方参考文档","Spring Boot Reference Guide","http://docs.spring.io/spring-boot/docs/1.5.1.RELEASE/reference/htmlsingle/#getting-started-first-application");
        voList.add(thymeleafVo);
        thymeleafVo =new ThymeleafVo("fred0","Spring Boot 0","http://gitlab.cloudoer.io/liuxk/spring-boot-template");
        voList.add(thymeleafVo);
        thymeleafVo =new ThymeleafVo("fred1","Spring Boot 1","http://gitlab.cloudoer.io/liuxk/spring-boot-template");
        voList.add(thymeleafVo);
        thymeleafVo =new ThymeleafVo("fred2","Spring Boot 2","http://gitlab.cloudoer.io/liuxk/spring-boot-template");
        voList.add(thymeleafVo);
        thymeleafVo =new ThymeleafVo("fred3","Spring Boot 3","http://gitlab.cloudoer.io/liuxk/spring-boot-template");
        voList.add(thymeleafVo);
        thymeleafVo =new ThymeleafVo("fred4","Spring Boot 4","http://gitlab.cloudoer.io/liuxk/spring-boot-template");
        voList.add(thymeleafVo);
        thymeleafVo =new ThymeleafVo("fred5","Spring Boot 5","http://gitlab.cloudoer.io/liuxk/spring-boot-template");
        voList.add(thymeleafVo);
        thymeleafVo =new ThymeleafVo("fred6","Spring Boot 6","http://gitlab.cloudoer.io/liuxk/spring-boot-template");
        voList.add(thymeleafVo);
        thymeleafVo =new ThymeleafVo("fred7","Spring Boot 7","http://gitlab.cloudoer.io/liuxk/spring-boot-template");
        voList.add(thymeleafVo);
        thymeleafVo =new ThymeleafVo("fred8","Spring Boot 8","http://gitlab.cloudoer.io/liuxk/spring-boot-template");
        voList.add(thymeleafVo);
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("voList", voList);

        return modelAndView;
    }
}
