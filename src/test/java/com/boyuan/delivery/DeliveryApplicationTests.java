package com.boyuan.delivery;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DeliveryApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void test() {
        try {
            throw new Exception("test exception");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
