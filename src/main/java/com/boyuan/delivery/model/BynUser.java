package com.boyuan.delivery.model;


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

    private long userId;

    private String userName;

    private String password;

    private long phone;

    private int sex;

    private int age;

    private Date birthday;

    private UserRole userRole;

    private Date createTime;

    private Date updateTime;

}
