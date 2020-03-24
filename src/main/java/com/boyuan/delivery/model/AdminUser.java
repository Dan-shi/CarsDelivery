/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

package com.boyuan.delivery.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AdminUser {

    private long adUserId;

    @NotBlank(message = "Admin user name should not be null or empty")
    private String adUserName;

    @NotBlank(message = "Admin user password should not be null or empty")
    private String password;

    @Email(message = "Admin user email format is not right")
    @NotBlank
    private String email;

    @NotBlank
    @Pattern(regexp = "^1(3|4|5|6|7|8|9)\\d{9}$", message = "phone number format wrong")
    private String phone;

    @Range(min=0, max=1, message = "Admin Sex can only be 0 or 1")
    private int sex;

    @Pattern(regexp = "/^(19|20)\\d{2}-(1[0-2]|0?[1-9])-(0?[1-9]|[1-2][0-9]|3[0-1])$/", message = "Birthday format is not right")
    private String birthday;

    private UserRole userRole;

    private boolean isActive;

    private Date createTime;

    private Date updateTime;

}
