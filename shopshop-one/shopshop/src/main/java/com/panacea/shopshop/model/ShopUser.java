package com.panacea.shopshop.model;

import lombok.Data;

@Data
public class ShopUser {

    private Double id;

    private String username;

    private String matchingPassword;

    private String password;

    private String email;

    private byte[] roles;

}