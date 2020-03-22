package com.boyuan.delivery.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BynUser {

    private long userId;

    @NotBlank(message = "User name should not be null or empty")
    private String userName;

    private String password;

    @NotBlank
    @Pattern(regexp = "^1(3|4|5|6|7|8|9)\\d{9}$", message = "phone number format wrong")
    private String phone;

    private int sex;

    private int age;

    @Pattern(regexp = "/^(19|20)\\d{2}-(1[0-2]|0?[1-9])-(0?[1-9]|[1-2][0-9]|3[0-1])$/", message = "Birthday format is not right")
    private String birthday;

    private UserRole userRole;

    private Date createTime;

    private Date updateTime;

}
