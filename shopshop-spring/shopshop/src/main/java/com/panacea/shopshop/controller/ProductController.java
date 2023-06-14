package com.panacea.shopshop.controller;

import java.util.List;

import com.panacea.shopshop.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	public Result<List<Product>> selectAll() {
		List<Product> productList = productMapper.selectAll();
//		Result<List<Product>> productResult = productList.success();
		return Result.success(productList,"ok");
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
