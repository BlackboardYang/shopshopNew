package com.panacea.shopshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class ShopshopApplicationTests {

    @Test
    void passwordGetter(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd0 = encoder.encode("admin");
        String pwd1 = encoder.encode("123");
        System.out.println("admin:" + pwd0 + "123:" + pwd1);
    }

}
