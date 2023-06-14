package com.panacea.shopshop.service.impl;

import com.panacea.shopshop.mapper.HeaderMapper;
import com.panacea.shopshop.mapper.ProductMapper;
import com.panacea.shopshop.model.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeaderServiceImpl implements HeaderMapper {

    @Autowired
    private HeaderMapper headerMapper;

    @Override
    public List<Header> selectAll() {
        return headerMapper.selectAll();
    }

}
