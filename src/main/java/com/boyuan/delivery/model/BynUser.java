package com.boyuan.delivery.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BynUser {

    private Long userId;

    @NotBlank(message = "User name should not be null or empty")
    private String userName;

    private String password;

    @NotBlank
    @Pattern(regexp = "^1(3|4|5|6|7|8|9)\\d{9}$", message = "phone number format wrong")
    private String phone;

    @Range(min=0, max=1, message = "Sex can only be 0 or 1")
    private int sex;

    @Range(min=1, max = 99, message = "Age range is 1 to 99")
    private int age;

    @Pattern(regexp = "/^(19|20)\\d{2}-(1[0-2]|0?[1-9])-(0?[1-9]|[1-2][0-9]|3[0-1])$/", message = "Birthday format is not right")
    private String birthday;

    private UserRole userRole;

    private Date createTime;

    private Date updateTime;

}
