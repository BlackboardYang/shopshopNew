package com.panacea.shopshop.mapper;

import com.panacea.shopshop.model.Product;
import com.panacea.shopshop.model.ProductExample;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductMapper {

	//@Insert("insert into product (id, parent_id, level, product_count, name, price,  description) values (#{id}, #{parentId}, #{level}, #{productCount}, #{name}, #{price}, #{description})")
	int insertProduct(Product row);

	//@Select("SELECT * FROM product WHERE id = #{id}")
	Product selectByPrimaryKey(Integer id);

	int deleteByPrimaryKey(Integer id);

	int updateByPrimaryKey(Product row);

	//@Select("SELECT * FROM product")
	List<Product> selectAll();
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product
	 * @mbg.generated  Mon Jun 19 01:12:43 JST 2023
	 */
	long countByExample(ProductExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product
	 * @mbg.generated  Mon Jun 19 01:12:43 JST 2023
	 */
	int deleteByExample(ProductExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product
	 * @mbg.generated  Mon Jun 19 01:12:43 JST 2023
	 */


	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product
	 * @mbg.generated  Mon Jun 19 01:12:43 JST 2023
	 */


	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product
	 * @mbg.generated  Mon Jun 19 01:12:43 JST 2023
	 */
	int insertSelective(Product row);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product
	 * @mbg.generated  Mon Jun 19 01:12:43 JST 2023
	 */
	List<Product> selectByExample(ProductExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product
	 * @mbg.generated  Mon Jun 19 01:12:43 JST 2023
	 */


	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product
	 * @mbg.generated  Mon Jun 19 01:12:43 JST 2023
	 */
	int updateByExampleSelective(@Param("row") Product row, @Param("example") ProductExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product
	 * @mbg.generated  Mon Jun 19 01:12:43 JST 2023
	 */
	int updateByExample(@Param("row") Product row, @Param("example") ProductExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product
	 * @mbg.generated  Mon Jun 19 01:12:43 JST 2023
	 */
	int updateByPrimaryKeySelective(Product row);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product
	 * @mbg.generated  Mon Jun 19 01:12:43 JST 2023
	 */


}