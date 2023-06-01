package com.panacea.shopshop.service.impl;

import com.panacea.shopshop.entity.Product;
import com.panacea.shopshop.mapper.ProductMapper;
import com.panacea.shopshop.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Alex
 * @since 2023-06-01
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
