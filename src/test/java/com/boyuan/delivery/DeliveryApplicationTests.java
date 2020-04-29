package com.boyuan.delivery;

import com.boyuan.delivery.common.utility.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class DeliveryApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void test() {
        String content = "<P>\n" +
                "                &nbsp;&nbsp;&nbsp;&nbsp;北京渤远物流有限公司简称（渤远运车）是一家专业从事汽车托运的物流企业，公司专注运营全国范围内私家车托运，二手车托运，巡展试驾车辆托运，主机厂保密车型托运，试验车托运，返乡团体车辆运输及自驾游车辆运输。\n" +
                "\n" +
                "                公司成立于2016年，总部位于北京顺义现代汽车第二工厂附近，经过多年努力发展经营，目前以设立32个省会级城市办事处，128个地方市级合作网点，325条重点城市专线，建立起遍布全国的整车汽车运输网络。</P>\n" +
                "\n" +
                "            <P> &nbsp;&nbsp;&nbsp;&nbsp;公司管理团队均为从事商品车物流多年的管理人才，拥有丰富的商品车物流管理经验，依托管理团队的丰富运营经验，先进的网络团队信息基础，超前的服务理念及遍布全国的物流服务网络布局，可为全国汽车商家及私家车用户提供更加快捷，更加省钱，更加方便，更加安全的服务，得到了广大车主的支持与信任。</P>\n" +
                "\n" +
                "            <p>&nbsp;&nbsp;&nbsp;&nbsp;公司目前自有正规轿运车逾70台，全国可调配正规轿运车超过500台，均为国家标准正规化运营车辆，不超载运行。保证承运车辆安全送达，让客户安心放心。我公司本着诚信第一，客户至上的服务态度为宗旨，竭诚为新老客户服务。</p>";

        System.out.println(JsonUtils.serialize(content));
    }

    @Test
    public void encryptPassword(){
        //Encrypt password
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encryptPwd = passwordEncoder.encode("123456");
        System.out.println(encryptPwd);
    }


}
