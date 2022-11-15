package com.consumer.rocketmq;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
        Integer type = rocketmqUserInfo.getType();
        long count = rocketmqUserInfoService.count(new LambdaQueryWrapper<RocketmqUserInfo>().eq(RocketmqUserInfo::getType, type));
        if (isTpye(count, type)) {
            rocketmqUserInfoService.save(rocketmqUserInfo);
        } else {
            RocketmqUserInfo rocketmqUserInfo1 = new RocketmqUserInfo();
            rocketmqUserInfo1.setType(-1);
            rocketmqUserInfoService.save(rocketmqUserInfo1);
        }
    }

    /**
     * 限制数量
     *
     * @param count  统计当前数量
     * @param typeId 优惠卷类别id
     * @return 条件符合true
     */
    protected Boolean isTpye(long count, Integer typeId) {
        if (typeId == 0 && count <= 100) {
            return true;
        } else if (typeId == 1 && count <= 200) {
            return true;
        } else if (typeId == 2 && count <= 300) {
            return true;
        } else if (typeId == 3 && count <= 400) {
            return true;
        }
        return false;
    }

}
