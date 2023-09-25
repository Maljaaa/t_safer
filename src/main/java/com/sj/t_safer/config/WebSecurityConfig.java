package com.sj.t_safer.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.sj.t_safer.handler.CustomLoginSuccessHandler;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { // 정적 자원에 대해서는 Security 설정을 적용하지 않음.

    private final UserDetailsService userDetailsService;

    @Override
    public void configure(WebSecurity web) {
        System.out.println("/config configure(web)");
        web
                .ignoring() // spring security 필터 타지 않도록 무시
                .antMatchers("/resources/**")
                .antMatchers("/h2-console/**"); // h2-console 무시
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("/config configure(http)");
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login", "/signup").permitAll()   // /login, /signup 은 인증 안해도 접근 가능하도록 설정
                .antMatchers("/admin").hasRole("ADMIN")         // /admin 은 어드민 유저만 가능하도록 설정
                .antMatchers("/monitor/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/my").authenticated()             // /my 은 인증이 되야함
                .and()
                .formLogin()                                                // form 을 통한 login 활성화
                .loginPage("/login")                                        // 로그인 페이지 URL 설정 , 설정하지 않는 경우 default 로그인 페이지 노출
                .successHandler(customLoginSuccessHandler())
                .failureForwardUrl("/fail")                                 // 로그인 실패 URL 설정
                .and()
                .logout()
                .logoutUrl("/logout")// 로그아웃 URL 설정
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
                            throws IOException {
                        System.out.println("jwt handle()");
                        // 권한 문제가 발생했을 때 이 부분을 호출한다.
                        response.setStatus(403);
                        response.setCharacterEncoding("utf-8");
                        response.setContentType("text/html; charset=UTF-8");
                        response.getWriter().write("권한이 없는 사용자입니다.");
                        System.out.println("403 Error");
                    }
                })
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
                            throws IOException {
                        System.out.println("jwt conmmence()");
                        // 인증문제가 발생했을 때 이 부분을 호출한다.
                        response.setStatus(401);
                        response.setCharacterEncoding("utf-8");
                        response.setContentType("text/html; charset=UTF-8");
                        response.getWriter().write("인증되지 않은 사용자입니다.");
                        System.out.println("401 Error");
                    }
                });
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        System.out.println("/config bCryptPasswordEncoder");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomLoginSuccessHandler customLoginSuccessHandler() {
        System.out.println("/config customLoginSuccessHandler()");
        return new CustomLoginSuccessHandler();
    }

    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider() {
        System.out.println("/config customAuthenticationProvider()");
        return new CustomAuthenticationProvider(userDetailsService, bCryptPasswordEncoder());
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
        System.out.println("/config configure(authenticationManagerBuilder");
        authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider());
    }
}
