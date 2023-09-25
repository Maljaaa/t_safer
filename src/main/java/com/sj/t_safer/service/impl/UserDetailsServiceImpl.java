package com.sj.t_safer.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.sj.t_safer.domain.sign.Users;
import com.sj.t_safer.repository.UserRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public Users loadUserByUsername(String userEmail) {
        System.out.println("loadUserByUsername");
        return Optional.ofNullable(userRepository.findByEmail(userEmail)).orElseThrow(() -> new BadCredentialsException("이메일 주소를 확인해주세요."));
    }

}