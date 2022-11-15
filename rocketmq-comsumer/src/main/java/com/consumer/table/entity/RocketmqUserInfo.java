package com.consumer.table.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author CHOOSE1
 * @since 2022-11-14
 */
@Data
@TableName("t_rocketmq_user_info")
@ApiModel(value = "RocketmqUserInfo对象", description = "")
public class RocketmqUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(type = IdType.AUTO)
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

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("优惠卷类别[0:200,1:100,2:50,3:10]")
    private Integer type;

}
