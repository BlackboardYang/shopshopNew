package com.panacea.shopshop.controller;

import com.panacea.shopshop.mapper.CategoryMapper;
import com.panacea.shopshop.model.Category;
import com.panacea.shopshop.service.impl.CategoryServiceImpl;
import com.panacea.shopshop.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class CategoryController {

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    CategoryServiceImpl categoryServiceImpl;

    @GetMapping("/home/category/all")
    public Result<List<Category>> findAllHeader() {
        List<Category> categoryList = categoryServiceImpl.selectAll();
        return Result.success(categoryList,"ok");
    }

//    @GetMapping("/home/category/cat-level")
//    public Result<List<Category>> findCategoryByLevel(@RequestParam("level") int level,
//                                                      @RequestParam("parent_id") int parentId) {
//        List<Category> categoryList = categoryServiceImpl.selectByLevel(level, parentId);
//        return Result.success(categoryList,"ok");
//    }

    @GetMapping("/home/category/cat-level")
    public Result<List<Category>> findGrandMenuItems(){
        List<Category> listGrandMenuItems= categoryServiceImpl.listWithChildren();
        return Result.success(listGrandMenuItems,"ok");
    }

}
