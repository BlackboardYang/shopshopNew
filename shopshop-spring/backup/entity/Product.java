package com.panacea.shopshop.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author Alex
 * @since 2023-06-01
 */
@ApiModel(value = "Product对象", description = "")
@Data
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private Object price;

}
