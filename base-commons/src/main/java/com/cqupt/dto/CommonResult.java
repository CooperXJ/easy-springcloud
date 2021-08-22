package com.cqupt.dto;

import cn.hutool.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 薛进
 * @version 1.0
 * @Description
 * @date 2021/8/8 7:49 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private int code;
    private String message;
    private T date;

    public CommonResult(int code, String message){
        this.code = code;
        this.message = message;
        this.date = null;
    }

    public static <T> CommonResult success(String message, T date) {
        return new CommonResult(HttpStatus.HTTP_OK,message,date);
    }

    public static CommonResult validateFailed(String message) {
        return new CommonResult(HttpStatus.HTTP_FORBIDDEN,message,null);
    }

    public static CommonResult fail(String message) {
        return new CommonResult(HttpStatus.HTTP_INTERNAL_ERROR,message,null);
    }
}
