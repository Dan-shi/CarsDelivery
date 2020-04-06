package com.boyuan.delivery.configuration;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
public class CommonConfigProperties {

    private long loginTokenExpireTime;

    private String emailSender;

    private String emailAuthCode;

    private String emailRecipients;
}
