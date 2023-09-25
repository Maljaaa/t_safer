package com.sj.t_safer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sj.t_safer.dto.UserDTO;
import com.sj.t_safer.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final UserRepository userRepository;


    @RequestMapping("/login")
    String loginView() {
        System.out.println("/login");
        return "login";
    }

    @RequestMapping("/success")
    String successView() {
        System.out.println("/success");
        return "success";
    }

    @RequestMapping("/fail")
    String failView() {
        System.out.println("/fail");
        return "fail";
    }

    @RequestMapping("/admin")
    ModelAndView adminView() {
        System.out.println("/admin");
        List<UserDTO> userDTOList = userRepository.findAll().stream().map(u -> UserDTO.builder().id(u.getId()).email(u.getEmail()).password(u.getPassword()).userRole(u.getUserRole()).build()).collect(Collectors.toList());
        ModelAndView modelAndView = new ModelAndView("/admin");
        modelAndView.addObject("userList", userDTOList);
        return modelAndView;
    }
    @RequestMapping("/monitor")
    ModelAndView monitorView() {
        System.out.println("/monitor");
        List<UserDTO> userDTOList = userRepository.findAll().stream().map(u -> UserDTO.builder().id(u.getId()).email(u.getEmail()).password(u.getPassword()).userRole(u.getUserRole()).build()).collect(Collectors.toList());
        ModelAndView modelAndView = new ModelAndView("/monitor");
        modelAndView.addObject("userList", userDTOList);
        return modelAndView;
    }

    @RequestMapping("/my")
    ModelAndView myView(Authentication authentication) {
        System.out.println("/my");
        UserDTO userDTO = Optional.ofNullable(userRepository.findByEmail(authentication.getName()))
                .map(u -> UserDTO.builder().id(u.getId()).email(u.getEmail()).password(u.getPassword()).userRole(u.getUserRole()).build())
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        ModelAndView modelAndView = new ModelAndView("/my");
        modelAndView.addObject("userDTO", userDTO);

        return modelAndView;
    }

    @RequestMapping("/signup")
    String signupView() {
        System.out.println("/signup");
        return "signup";
    }


}
