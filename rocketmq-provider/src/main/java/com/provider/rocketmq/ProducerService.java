package com.provider.rocketmq;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 消息生产者service
 *
 * @author CHOOSE1
 * @date 2022-11-13 11:31:01
 */
@Component(value = "producerService")
@RocketMQTransactionListener
public class ProducerService implements RocketMQLocalTransactionListener {

    public static final int COUNT = 100;

    private final RocketMQTemplate rocketMQTemplate;

    public ProducerService(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    /**
     * 发送简单消息
     */
    public void sendMessage() {
        for (int i = 0; i < COUNT; i++) {
            rocketMQTemplate.convertAndSend("lsx-rocketmq", "rocketmq hello seqId：" + i);
        }
    }

    /**
     * 发送同步消息
     */
    public void sendSyncMessage() {
        for (int i = 0; i < COUNT; i++) {
            SendResult sendResult = rocketMQTemplate.syncSend("lsx-rocketmq", "rocketmq hello seqId：" + i);
        }
    }

    /**
     * 发送异步消息 使用比较频繁
     */
    public void sendAsyncMessage() {
        for (int i = 0; i < COUNT; i++) {
            rocketMQTemplate.asyncSend("lsx-rocketmq", "rocketmq hello seqId：" + i, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("成功" + sendResult);
                }

                @Override
                public void onException(Throwable throwable) {
                    System.out.println("失败处理");
                }
            });
        }
    }

    /**
     * 发送单项消息
     */
    public void sendOneWayMessage() {
        for (int i = 0; i < COUNT; i++) {
            rocketMQTemplate.sendOneWay("lsx-rocketmq", "rocketmq hello seqId：" + i);
        }
    }

    /**
     * 发送同步顺序消息
     */
    public void sendOrderlyMessage() {
        for (int i = 0; i < COUNT; i++) {
            String orderNo = UUID.randomUUID().toString();
            rocketMQTemplate.syncSendOrderly("lsx-rocketmq-orderly", orderNo + ",订单创建", orderNo);
            rocketMQTemplate.syncSendOrderly("lsx-rocketmq-orderly", orderNo + ",订单支付", orderNo);
            rocketMQTemplate.syncSendOrderly("lsx-rocketmq-orderly", orderNo + ",订单完成", orderNo);
        }
    }

    /**
     * 发送延迟消息
     */
    public void sendDelayMessage() {
        rocketMQTemplate.syncSend("lsx-rocketmq-orderly", MessageBuilder.withPayload("延时1s消息").build(), 3000, 1);
        rocketMQTemplate.syncSend("lsx-rocketmq-orderly", MessageBuilder.withPayload("延时5s消息").build(), 3000, 2);
        rocketMQTemplate.syncSend("lsx-rocketmq-orderly", MessageBuilder.withPayload("延时10s消息").build(), 3000, 3);
    }

    /**
     * 发送事务消息
     */
    public void sendTransactionMessage() {
        //构造消息
        Message<String> msg = MessageBuilder.withPayload("rocketmq事务消息").build();
        rocketMQTemplate.sendMessageInTransaction("lsx-rocketmq-orderly", msg, null);
    }

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        System.out.println("executeLocalTransaction");
        return RocketMQLocalTransactionState.UNKNOWN;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        System.out.println("checkLocalTransaction");
        return RocketMQLocalTransactionState.COMMIT;
    }

    /**
     * 发送tag消息，测试根据tag过滤消息
     */
    public void sendMessageWithTag() {
        Message<String> msg1 = MessageBuilder.withPayload("rocketmq过滤消息1").build();
        Message<String> msg2 = MessageBuilder.withPayload("rocketmq过滤消息2").build();
        Message<String> msg3 = MessageBuilder.withPayload("rocketmq过滤消息3").build();
        rocketMQTemplate.convertAndSend("lsx-rocketmq-orderly" + ":" + "TAG1", msg1);
        rocketMQTemplate.convertAndSend("lsx-rocketmq-orderly" + ":" + "TAG2", msg2);
        rocketMQTemplate.convertAndSend("lsx-rocketmq-orderly" + ":" + "TAG3", msg3);
    }

    /**
     * 根据sql表达式头信息消息，测试根据sql表达式过滤消息
     */
    public void sendMessageWithSql() {

        // 构造消息1
        Message<String> msg1 = MessageBuilder.withPayload("rocketmq 过滤消息测试1").build();
        Map<String, Object> headers1 = new HashMap<>(16);
        headers1.put("type", "pay");
        headers1.put("a", 10);
        rocketMQTemplate.convertAndSend("lsx-rocketmq-orderly", msg1, headers1);

        // 构造消息2
        Message<String> msg2 = MessageBuilder.withPayload("rocketmq 过滤消息测试1").build();
        Map<String, Object> headers2 = new HashMap<>(16);
        headers2.put("type", "store");
        headers2.put("a", 4);
        rocketMQTemplate.convertAndSend("lsx-rocketmq-orderly", msg2, headers2);

        // 构造消息3
        Message<String> msg3 = MessageBuilder.withPayload("rocketmq 过滤消息测试1").build();
        Map<String, Object> headers3 = new HashMap<>(16);
        headers3.put("type", "user");
        headers3.put("a", 7);
        rocketMQTemplate.convertAndSend("lsx-rocketmq-orderly", msg3, headers3);

    }

}
