package com.buoyuan.delivery.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BynUser {

    private long id;

    private String userName;

    private String password;

    private int phone;

    private int sex;

    private int age;

    private Date birthday;

    private int userType;

    private Date createTime;

    private Date updateTime;

}
