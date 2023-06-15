package com.panacea.shopshop.model;

import lombok.Data;

@Data
public class Product {


	private Integer id;

	private Integer parentId;

	private Integer level;

	private String name;

	private Double price;

	private String productCount;

}