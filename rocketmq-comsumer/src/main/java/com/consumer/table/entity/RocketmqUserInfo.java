package com.consumer.table.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author CHOOSE1
 * @since 2022-11-14
 */
@TableName("t_rocketmq_user_info")
@ApiModel(value = "RocketmqUserInfo对象", description = "")
public class RocketmqUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("名字")
    private String userName;

    @ApiModelProperty("昵称")
    private String nikeName;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("手机")
    private String phone;

    @ApiModelProperty("城市")
    private String city;

    @ApiModelProperty("性别")
    private Integer sex;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "RocketmqUserInfo{" +
                "id = " + id +
                ", userName = " + userName +
                ", nikeName = " + nikeName +
                ", address = " + address +
                ", phone = " + phone +
                ", city = " + city +
                ", sex = " + sex +
                "}";
    }
}
