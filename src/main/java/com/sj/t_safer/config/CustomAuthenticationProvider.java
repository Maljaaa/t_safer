package com.sj.t_safer.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.sj.t_safer.domain.sign.Users;

/**
 * AuthenticationProvider
 * 실제 인증을 담당하는 인터페이스
 */
@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("/config authenticate()");
        // DB 에서 사용자 정보가 실제로 유효한지 확인 후 인증된 Authentication 리턴
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication; // AuthenticaionFilter에서 생성된 토큰으로부터 아이디와 비밀번호를 조회함
        System.out.println("token = " + token);
        String userEmail = token.getName();
        System.out.println("userEmail = " + userEmail);
        String userPassWord = (String) token.getCredentials(); // UserDetailsService를 통해 DB에서 아이디로 사용자 조회
        System.out.println("userPassWord = " + userPassWord);

        Users users = (Users) userDetailsService.loadUserByUsername(userEmail);
        if (passwordEncoder.matches(userPassWord, users.getPassword()) == false) {
            throw new BadCredentialsException(users.getUsername() + " 비밀번호를 확인해주세요.");
        }
        return new UsernamePasswordAuthenticationToken(users, userPassWord, users.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        System.out.println("/config supports()");
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}