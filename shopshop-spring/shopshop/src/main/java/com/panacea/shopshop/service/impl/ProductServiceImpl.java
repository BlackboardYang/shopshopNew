package com.panacea.shopshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.apache.ibatis.session.SqlSession;


import com.panacea.shopshop.mapper.ProductMapper;
import com.panacea.shopshop.model.Product;
import com.panacea.shopshop.model.ProductExample;

@Service
@Component
public class ProductServiceImpl implements ProductMapper{

	@Autowired
	private ProductMapper productMapper;


	@Override
	public List<Product> selectAll() {
		return productMapper.selectAll();
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return productMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(Product row) {
		return productMapper.updateByPrimaryKey(row);
	}

	@Override
	public Product selectByPrimaryKey(Integer id) {
		return productMapper.selectByPrimaryKey(id);
	}

//	private final SqlSession sqlSession;
//	public ProductServiceImpl(SqlSession sqlSession) {
//		this.sqlSession = sqlSession;
//	}
//	@Override
//	public Product selectByPrimaryKey(Integer id) {
//		//return productMapper.selectByPrimaryKey(id);
//		return this.sqlSession.selectOne("selectByPrimaryKey", id);
//	}

	@Override
	public Product insert(Product row) {
		return productMapper.insert(row);
	}


	@Override
	public long countByExample(ProductExample example) {
		return 0;
	}

	@Override
	public int deleteByExample(ProductExample example) {
		return 0;
	}

	@Override
	public int insertSelective(Product row) {
		return 0;
	}

	@Override
	public List<Product> selectByExample(ProductExample example) {
		return null;
	}

	@Override
	public int updateByExampleSelective(Product row, ProductExample example) {
		return 0;
	}

	@Override
	public int updateByExample(Product row, ProductExample example) {
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(Product row) {
		return 0;
	}


}
