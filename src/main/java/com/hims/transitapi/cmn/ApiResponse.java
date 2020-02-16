package com.hims.transitapi.cmn;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("rawtypes")
public class ApiResponse<T> {
    private static ApiResponse instance = new ApiResponse();
    private T response;
    private String message;
    private int statusCode;

    private ApiResponse() {
    }

    public static ApiResponse getInstance() {
        return instance;
    }
}
