package com.panacea.shopshop.controller;

import java.util.List;

import com.panacea.shopshop.model.ProductExample;
import com.panacea.shopshop.service.impl.ProductServiceImpl;
import com.panacea.shopshop.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.panacea.shopshop.mapper.ProductMapper;
import com.panacea.shopshop.model.Product;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductMapper productMapper;

	@Autowired
	ProductServiceImpl productService;


	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public Result<Product> insert(@RequestBody Product product) {
		int result = productService.insertProduct(product);
		return (result == 0)? Result.fail("Add Fail") : Result.success("ok");
	}
	
	@PutMapping("/updateById")
	public Result<Product> updateById(@RequestBody Product product) {
		productService.updateByPrimaryKey(product);
		Integer id = product.getId();
		return Result.success("id: " + id + "Information updated!");
	}
	
	@GetMapping("/selectAllProduct")
	public Result<List<Product>> selectAll() {
		List<Product> productList = productMapper.selectAll();
//		Result<List<Product>> productResult = productList.success();
		return Result.success(productList,"ok");
	}

	@RequestMapping(value = "/selectById", method = RequestMethod.GET)
	public Result<Product> selectById(@RequestParam("id") Integer id){
		System.out.println("Select by id controller runs!!!!!!");
		Product result = productService.selectByPrimaryKey(id);
		return Result.success(result,"ok");
		
	}

	@RequestMapping(value = "/deleteById", method = RequestMethod.GET)
	public Result<Product> deleteById(@RequestParam("id") Integer id) {
		productService.deleteByPrimaryKey(id);
		return Result.success("id: " + id + "deleted success!");
	}
	

}
