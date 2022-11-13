package com.consumer.rocketmq;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 消费者服务类
 *
 * @author CHOOSE1
 * @date 2022-11-13 11:45:56
 */
@Component
@RocketMQMessageListener(topic = "lsx-rocketmq-orderly", consumerGroup = "${rocketmq.consumer.group}", selectorExpression = "TAG1 || TAG2", selectorType = SelectorType.TAG)
public class ConsumerService implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        System.out.println("consumer收到消息内容:" + message + DateFormatUtils.format(new Date(), " *yyyy-MM-dd HH:mm:ss"));
    }

}
