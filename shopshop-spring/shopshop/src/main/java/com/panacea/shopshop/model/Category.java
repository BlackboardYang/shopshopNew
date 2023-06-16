package com.panacea.shopshop.model;

import lombok.Data;

import java.util.List;

@Data
public class Category {

    private Integer id;

    private Integer parentId;

    private Integer level;

    private String name;

    private List<Category> children;

}
