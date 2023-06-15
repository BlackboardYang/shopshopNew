package com.panacea.shopshop.service.impl;

import com.panacea.shopshop.mapper.CategoryMapper;

import com.panacea.shopshop.model.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryMapper {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> selectAll() {
        return categoryMapper.selectAll();
    }

    @Override
    public List<Category> selectByLevel(int level, int parentId) {
        return categoryMapper.selectByLevel(level, parentId);
    }

    @Override
    public int deleteByPrimaryKey(int id) {
        return 0;
    }

    @Override
    public int insert(int id, int parentId, int level, String name) {
        return 0;
    }


}
