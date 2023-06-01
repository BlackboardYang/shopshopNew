package com.panacea.shopshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.panacea.shopshop.mapper.ProductMapper;
import com.panacea.shopshop.model.Product;

@RestController
@RequestMapping
public class ProductController {
	
	@Autowired
	ProductMapper productMapper;
	
	@PostMapping("/insert")
	public int insert(Product product) {
		return productMapper.insert(product);
		
	}
	
	@PutMapping("/updateById")
	public int updateById(Product product) {
		return productMapper.updateByPrimaryKey(product);
		
	}
	
	@GetMapping("/selectAll")
	public List<Product> selectAll() {
		return productMapper.selectAll();
		
	}
	
	@GetMapping("/selectById/{id}")
	public Product selectById(@PathVariable("id") String id){
		return productMapper.selectByPrimaryKey(id);
		
	}
	
	@DeleteMapping("/deleteById/{id}")
	public int deleteById(@PathVariable("id") String id) {
		return productMapper.deleteByPrimaryKey(id);
	}
	

}
