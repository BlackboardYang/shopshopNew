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
@ApiModel(value = "User对象", description = "")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String firstName;

    private String email;

    private String lastName;

    private String matchingPassword;

    private String password;

    private byte[] roles;

}
