package com.cloudoer.project.project.module.quartz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/9/6
 */
@Component
@Configurable
@EnableScheduling
@Slf4j
public class SendMailQuartz {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Scheduled(cron = "0 0/30 * * * ?")
    public void send() throws MessagingException {
        log.info("定时器开始发邮件了");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setSubject("this is from schedule mail sender");
        mimeMessageHelper.setTo("liuxk@cloudoer.com");
        mimeMessageHelper.setText("mail content information");

        this.javaMailSender.send(mimeMessage);
        log.info("定时器发完邮件了");
    }
}
