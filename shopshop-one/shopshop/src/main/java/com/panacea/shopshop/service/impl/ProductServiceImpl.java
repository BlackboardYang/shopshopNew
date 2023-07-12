package com.panacea.shopshop.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
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

	@Autowired
	private SqlSessionFactory sqlSessionFactory;


	@Override
	public List<Product> selectAll() {
		return productMapper.selectAll();
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		productMapper.deleteByPrimaryKey(id);
		return id;
	}

//	@Override
//	public int insert(Product row) {
//		productMapper.insert(row);
//		int affactedEntryNum =
//		return ;
//	}


	// 构造方法或注入方式设置SqlSessionFactory

	@Override
	public int insertProduct(Product row) {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			int rowsAffected = session.insert("insertProduct", row);
			session.commit();
			return rowsAffected;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
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
