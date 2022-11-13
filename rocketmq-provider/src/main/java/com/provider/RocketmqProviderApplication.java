package com.provider;

import com.provider.rocketmq.ProducerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author CHOOSE1
 * @date 2022-11-13 11:36:55
 */
@SpringBootApplication
public class RocketmqProviderApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(RocketmqProviderApplication.class, args);
        ProducerService producerService = (ProducerService) run.getBean("producerService");
        producerService.sendMessageWithTag();
    }

}
