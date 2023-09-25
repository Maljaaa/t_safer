package com.sj.t_safer.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import com.sj.t_safer.domain.sign.UserRole;

@Getter
@Setter
public class UserCreateRequest {

    @NotNull
    private String email;

    @NotNull
    private String passWord;

    @NotNull
    private UserRole userRole;

    public UserCreateRequest(String email, String passWord, UserRole userRole) {
        System.out.println("UserCreateRequest");
        this.email = email;
        this.passWord = passWord;
        this.userRole = userRole;
    }
}
