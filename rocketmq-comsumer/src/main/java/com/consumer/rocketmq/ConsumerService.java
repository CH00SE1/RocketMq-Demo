package com.consumer.rocketmq;

import com.alibaba.fastjson2.JSONObject;
import com.consumer.table.entity.RocketmqUserInfo;
import com.consumer.table.service.IRocketmqUserInfoService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
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
// , selectorExpression = "TAG1 || TAG2", selectorType = SelectorType.TAG
@RocketMQMessageListener(topic = "lsx-rocketmq", consumerGroup = "${rocketmq.consumer.group}")
public class ConsumerService implements RocketMQListener<String> {

    final IRocketmqUserInfoService rocketmqUserInfoService;

    public ConsumerService(IRocketmqUserInfoService rocketmqUserInfoService) {
        this.rocketmqUserInfoService = rocketmqUserInfoService;
    }


    @Override
    public void onMessage(String message) {
        System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS") + " message:" + message);
        RocketmqUserInfo rocketmqUserInfo = JSONObject.parseObject(message, RocketmqUserInfo.class);
        rocketmqUserInfoService.save(rocketmqUserInfo);
    }

}
