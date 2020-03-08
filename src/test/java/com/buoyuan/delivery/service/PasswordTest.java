package com.buoyuan.delivery.service;


import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordTest {

    @Test
    public void testPassword(){
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String password = "Dayuanlian123";
        String encryptPassword = passwordEncoder.encode(password);
        System.out.println("password: "+encryptPassword);
    }
}
