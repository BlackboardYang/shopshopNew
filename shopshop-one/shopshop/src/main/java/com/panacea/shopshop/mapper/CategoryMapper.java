package com.panacea.shopshop.mapper;

import com.panacea.shopshop.model.Category;
import org.apache.ibatis.annotations.*;

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

//    @Results(id = "CategoryResultMap", value = {
//            @Result(property = "id", column = "id"),
//            @Result(property = "parentId", column = "parent_id"),
//            @Result(property = "level", column = "level"),
//            @Result(property = "name", column = "name"),
//            @Result(property = "children", column = "id", javaType = List.class,
//                    many = @Many(select = "com.panacea.shopshop.mapper.CategoryMapper.listWithChildren"))
//    })
//    @Select("SELECT c1.id, c1.name, c2.id AS child_id, c2.name AS child_name " +
//            "FROM category c1 " +
//            "LEFT JOIN category c2 ON c1.id = c2.parent_id " +
//            "WHERE c1.parent_id = 0")
    List<Category> listWithChildren();


}
