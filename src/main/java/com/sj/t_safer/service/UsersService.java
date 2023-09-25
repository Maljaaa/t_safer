package com.sj.t_safer.service;

import com.sj.t_safer.dto.UserCreateRequest;
import com.sj.t_safer.dto.UserDTO;

public interface UsersService {
    UserDTO createUser(UserCreateRequest userCreateRequest);
}
