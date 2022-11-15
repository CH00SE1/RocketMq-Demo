package com.provider.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CHOOSE1
 * @date 2022-11-14 21:43:09
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RocketmqUserInfo {

    private String userName;

    private String nikeName;

    private String address;

    private String phone;

    private String city;

    private Integer sex;

    private Integer type;

}
