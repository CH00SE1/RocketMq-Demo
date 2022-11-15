package com.provider.controller;

import com.provider.entity.RocketmqUserInfo;
import com.provider.rocketmq.ProducerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author CHOOSE1
 * @since 2022-11-15
 */
@RestController
@RequestMapping("/rocketmqUserInfo")
public class RocketmqUserInfoController {

    @Resource
    private ProducerService producerService;

    @PostMapping(value = "/testRocketmq")
    public String testRocketmq(@RequestBody RocketmqUserInfo rocketmqUserInfo) {
        producerService.sendAsyncMessage(rocketmqUserInfo);
        return "收到";
    }

}
