package com.sj.t_safer.service.impl;

import com.sj.t_safer.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.sj.t_safer.dto.UserCreateRequest;
import com.sj.t_safer.dto.UserDTO;
import com.sj.t_safer.domain.sign.Users;
import com.sj.t_safer.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UsersService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDTO createUser(UserCreateRequest userCreateRequest) {
        System.out.println("createUser");
        Users users = userRepository.save(
                Users.builder().password(bCryptPasswordEncoder.encode(userCreateRequest.getPassWord())).email(userCreateRequest.getEmail()).userRole(userCreateRequest.getUserRole()).build());
        return UserDTO.builder().id(users.getId()).password(users.getPassword()).userRole(users.getUserRole()).email(users.getEmail()).build();
    }

}
