package com.lin.demoes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto<T> {

    private boolean success;

    private T data;

    private String errorMessage;

    public static <U> ResponseDto<U> success(U data){
        ResponseDto<U> response = new ResponseDto<U>();
        response.success = true;
        response.setData(data);
        return response;
    }

    public static ResponseDto<?> error(String errorMessage){
        ResponseDto<?> response = new ResponseDto<>();
        response.success = false;
        response.setErrorMessage(errorMessage);
        return response;
    }
}
