package com.cloudoer.project.project.module.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/9/6
 */
@Component
@Slf4j
public class SimpleConsumer {


    @JmsListener(destination = "test-queue", containerFactory = "queueListenerFactory")
    public void consumeSimpleQueue(String text) {
        log.info("消费了一条消息：{}", text);
    }

    @JmsListener(destination = "test-topic", containerFactory = "topicListenerFactory")
    public void consumeSimpleTopic(String text) {
        log.info("topic1消费了一条消息：{}", text);
    }

    @JmsListener(destination = "test-topic", containerFactory = "topicListenerFactory")
    public void consumeSimpleTopic2(TextMessage text) throws JMSException {
        log.info("topic2消费了一条消息：{}", text.getText());
    }

}
