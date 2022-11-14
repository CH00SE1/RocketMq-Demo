package com.provider.entity;

/**
 * @author CHOOSE1
 * @date 2022-11-14 21:43:09
 */
public class RocketmqUserInfo {

    private Integer id;

    private String userName;

    private String nikeName;

    private String address;

    private String phone;

    private String city;

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
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", nikeName='" + nikeName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", sex=" + sex +
                '}';
    }
}
