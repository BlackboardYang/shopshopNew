package com.panacea.shopshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.panacea.shopshop.mapper.ProductMapper;

@SpringBootApplication
public class ShopshopApplication{

	public static void main(String[] args) {
		SpringApplication.run(ShopshopApplication.class, args);
	}
//	
//	private final ProductMapper productMapper;
//	
//	public ShopshopApplication(ProductMapper productMapper) {
//		this.productMapper = productMapper;
//	}

}
