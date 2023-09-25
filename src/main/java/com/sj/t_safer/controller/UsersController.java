package com.sj.t_safer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sj.t_safer.dto.PackageDto;
import com.sj.t_safer.dto.UserCreateRequest;
import com.sj.t_safer.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserServiceImpl usersService;

    @GetMapping
    public PackageDto getPackageDto() {
        System.out.println("getPackageDto()");

        PackageDto packageDto = new PackageDto();
        packageDto.setNew(false);

        return packageDto;
    }

    @PostMapping("/signup")
    public void createUser(UserCreateRequest userCreateRequest, HttpServletResponse response) throws IOException {
        System.out.println("/signup");
        usersService.createUser(userCreateRequest);
        response.sendRedirect("/login");
    }

    @GetMapping("/logout")
    public void logoutPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("/logout");
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        response.sendRedirect("/login");
    }


}
