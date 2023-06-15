package com.panacea.shopshop.model;

import lombok.Data;

@Data
public class Category {

    private Integer id;

    private Integer parentId;

    private Integer level;

    private String name;

}
