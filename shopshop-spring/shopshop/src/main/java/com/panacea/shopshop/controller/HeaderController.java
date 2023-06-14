package com.panacea.shopshop.controller;

import com.panacea.shopshop.mapper.HeaderMapper;
import com.panacea.shopshop.mapper.ProductMapper;
import com.panacea.shopshop.model.Header;
import com.panacea.shopshop.model.Product;
import com.panacea.shopshop.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class HeaderController {

    @Autowired
    HeaderMapper headerMapper;

    @GetMapping("/home/category/head")
    public Result<List<Header>> findAllHeader() {
        List<Header> headerList = headerMapper.selectAll();
        return Result.success(headerList,"ok");
    }
}
