package com.panacea.shopshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.panacea.*.mapper")
public class ShopshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopshopApplication.class, args);
	}

}
