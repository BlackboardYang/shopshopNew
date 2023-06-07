package com.panacea.shopshop.mapper;

import com.panacea.shopshop.model.ShopUser;
import com.panacea.shopshop.model.ShopUserExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ShopUserMapper {

    @Select("SELECT * FROM shop_user WHERE username = #{text} or email = #{text}")
    ShopUser searchByNameOrEmail(String text);



//
//    ShopUser selectByEmailOrUsername(@Param("username") String username);
//
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table shop_user
//     *
//     * @mbg.generated Mon Jun 05 12:08:40 JST 2023
//     */
//    long countByExample(ShopUserExample example);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table shop_user
//     *
//     * @mbg.generated Mon Jun 05 12:08:40 JST 2023
//     */
//    int deleteByExample(ShopUserExample example);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table shop_user
//     *
//     * @mbg.generated Mon Jun 05 12:08:40 JST 2023
//     */
//    int deleteByPrimaryKey(Double id);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table shop_user
//     *
//     * @mbg.generated Mon Jun 05 12:08:40 JST 2023
//     */
//    int insert(ShopUser row);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table shop_user
//     *
//     * @mbg.generated Mon Jun 05 12:08:40 JST 2023
//     */
//    int insertSelective(ShopUser row);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table shop_user
//     *
//     * @mbg.generated Mon Jun 05 12:08:40 JST 2023
//     */
//    List<ShopUser> selectByExampleWithBLOBs(ShopUserExample example);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table shop_user
//     *
//     * @mbg.generated Mon Jun 05 12:08:40 JST 2023
//     */
//    List<ShopUser> selectByExample(ShopUserExample example);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table shop_user
//     *
//     * @mbg.generated Mon Jun 05 12:08:40 JST 2023
//     */
//    ShopUser selectByPrimaryKey(Double id);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table shop_user
//     *
//     * @mbg.generated Mon Jun 05 12:08:40 JST 2023
//     */
//    int updateByExampleSelective(@Param("row") ShopUser row, @Param("example") ShopUserExample example);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table shop_user
//     *
//     * @mbg.generated Mon Jun 05 12:08:40 JST 2023
//     */
//    int updateByExampleWithBLOBs(@Param("row") ShopUser row, @Param("example") ShopUserExample example);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table shop_user
//     *
//     * @mbg.generated Mon Jun 05 12:08:40 JST 2023
//     */
//    int updateByExample(@Param("row") ShopUser row, @Param("example") ShopUserExample example);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table shop_user
//     *
//     * @mbg.generated Mon Jun 05 12:08:40 JST 2023
//     */
//    int updateByPrimaryKeySelective(ShopUser row);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table shop_user
//     *
//     * @mbg.generated Mon Jun 05 12:08:40 JST 2023
//     */
//    int updateByPrimaryKeyWithBLOBs(ShopUser row);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table shop_user
//     *
//     * @mbg.generated Mon Jun 05 12:08:40 JST 2023
//     */
//    int updateByPrimaryKey(ShopUser row);
}