package com.panacea.shopshop.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    //success
    public static <T> Result<T> success(){
        return new Result<>(200, "success", null);
    }

    public static <T> Result<T> success(T data){
        return new Result<>(200, "success", data);
    }

    public static <T> Result<T> success(T data, String message){
        return new Result<>(200, message, data);
    }

    public static <T> Result<T> success(String message){
        return new Result<>(200, message, null);
    }

    //fail
    public static <T> Result<T> fail(){
        return new Result<>(201, "fail", null);
    }

    public static <T> Result<T> fail(T data){
        return new Result<>(201, "success", data);
    }

    public static <T> Result<T> fail(T data, String message){
        return new Result<>(201, message, data);
    }

    public static <T> Result<T> fail(String message){
        return new Result<>(201, message, null);
    }
}
