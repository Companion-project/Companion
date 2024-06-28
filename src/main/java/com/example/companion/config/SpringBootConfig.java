package com.example.companion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Component
public class SpringBootConfig {

    //암호화 객체 생성
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //security 로그인 화면을 사용하지 않게 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)
            throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())  // CSRF 방지 비활성화
                .formLogin(formLogin -> formLogin.disable());  // 로그인 화면 비활성화
        return httpSecurity.build();
    }

    //ModelAndView 객체 생성
    @Bean(value = "jsonView")
    public MappingJackson2JsonView jsonView(){
        return new MappingJackson2JsonView();
    }
}