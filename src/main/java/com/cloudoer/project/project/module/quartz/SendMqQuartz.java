package com.cloudoer.project.project.module.quartz;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.Message;
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
public class SendMqQuartz {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Scheduled(cron = "15/15 * * * * ?")
    public void produce() {
        log.info("定时器开始发MQ-queue消息了");

        Destination queue = new ActiveMQQueue("test-queue");
        for (int i = 0; i < 100; i++) {
            jmsMessagingTemplate.convertAndSend(queue, "hello.mq, queue, message!" + i);
        }
        log.info("定时器发完MQ-queue消息了");

        log.info("定时器开始发MQ-topic消息了");
        Destination topic = new ActiveMQTopic("test-topic");

        for (int i = 0; i < 100; i++) {
            jmsMessagingTemplate.convertAndSend(topic, "hello, mq ,topic" + i);
        }
        log.info("定时器发完MQ-topic消息了");
    }
}
