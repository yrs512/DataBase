package org.example.response;

import lombok.Getter;

@Getter
public class LoginResponse {
    private int code;
    private Object data;
    private String msg;

    public LoginResponse(int code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

}
