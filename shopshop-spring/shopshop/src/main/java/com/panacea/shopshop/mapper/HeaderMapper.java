package com.panacea.shopshop.mapper;

import com.panacea.shopshop.model.Header;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HeaderMapper {

    @Select("SELECT * FROM header")
    List<Header> selectAll();
}
