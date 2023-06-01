package com.panacea.shopshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panacea.shopshop.mapper.ProductMapper;
import com.panacea.shopshop.model.Product;
import com.panacea.shopshop.model.ProductExample;

@Service
public class ProductServiceImpl implements ProductMapper{
	
	@Autowired
	private ProductMapper productMapper;

	@Override
	public List<Product> selectAll() {
		// TODO Auto-generated method stub
		return productMapper.selectAll();
	}

	@Override
	public long countByExample(ProductExample example) {
		// TODO Auto-generated method stub
		return productMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(ProductExample example) {
		// TODO Auto-generated method stub
		return productMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return productMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Product row) {
		// TODO Auto-generated method stub
		return productMapper.insert(row);
	}

	@Override
	public int insertSelective(Product row) {
		// TODO Auto-generated method stub
		return productMapper.insertSelective(row);
	}

	@Override
	public List<Product> selectByExample(ProductExample example) {
		// TODO Auto-generated method stub
		return productMapper.selectByExample(example);
	}

	@Override
	public Product selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return productMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(Product row, ProductExample example) {
		// TODO Auto-generated method stub
		return productMapper.updateByExampleSelective(row, example);
	}

	@Override
	public int updateByExample(Product row, ProductExample example) {
		// TODO Auto-generated method stub
		return productMapper.updateByExample(row, example);
	}

	@Override
	public int updateByPrimaryKeySelective(Product row) {
		// TODO Auto-generated method stub
		return productMapper.updateByPrimaryKeySelective(row);
	}

	@Override
	public int updateByPrimaryKey(Product row) {
		// TODO Auto-generated method stub
		return productMapper.updateByPrimaryKey(row);
	}
	

}
