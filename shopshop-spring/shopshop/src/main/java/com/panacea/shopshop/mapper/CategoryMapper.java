package com.panacea.shopshop.mapper;

import com.panacea.shopshop.model.Category;
import com.panacea.shopshop.model.Header;
import com.panacea.shopshop.model.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select("SELECT * FROM category")
    List<Category> selectAll();

    @Select("SELECT * FROM category WHERE level = #{level} AND parent_id = #{parentId}")
    List<Category> selectByLevel(int level, int parentId);

    @Delete("DELETE FROM category WHERE id = #{id}")
    int deleteByPrimaryKey(int id);

    @Insert("INSERT INTO shop_user (id, parent_id, level, name) VALUES (#{id}, #{parentId}, #{level}, #{name})")
    int insert(int id , int parentId ,int level ,String name);


}
