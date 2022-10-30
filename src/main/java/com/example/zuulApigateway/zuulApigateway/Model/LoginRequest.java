package com.example.zuulApigateway.zuulApigateway.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private String userName;

    private String password;

    public String getUserName() {
        return userName;
    }
}
