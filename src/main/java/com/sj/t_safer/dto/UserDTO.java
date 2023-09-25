package com.sj.t_safer.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.sj.t_safer.domain.sign.UserRole;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String email;

    private String password;

    private UserRole userRole;

    @Builder
    private UserDTO(Long id, String password, UserRole userRole, String email) {
        System.out.println("UserDTO");
        this.id = id;
        this.password = password;
        this.userRole = userRole;
        this.email = email;
    }

}
