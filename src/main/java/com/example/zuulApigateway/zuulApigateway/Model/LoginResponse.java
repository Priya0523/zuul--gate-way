package com.example.zuulApigateway.zuulApigateway.Model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class LoginResponse {

    private String jwt;
    private String loginStatus;
    private UserRegistration user;

    public LoginResponse(String jwt, String loginStatus, UserRegistration user) {
        this.jwt=jwt;
        this.loginStatus=loginStatus;
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public UserRegistration getUser() {
        return user;
    }
}
