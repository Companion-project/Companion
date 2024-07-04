package com.example.companion.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    InteceptorConfig inteceptorConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludeList = new ArrayList<String>();
        excludeList.add("/register/**/*");
        excludeList.add("/checkRest/**/*");
        excludeList.add("/help/**/*");
        excludeList.add("/login/**/*");
        excludeList.add("/corner/**/*");
        excludeList.add("/review/**/*");
        excludeList.add("/inquire/**/*");
        excludeList.add("/static/**");
        excludeList.add("/userConfirm");
        excludeList.add("/category");
        excludeList.add("/test");
//        excludeList.add("/loginForm");
        //허용페이지 이외에는 다 차단
        registry.addInterceptor(inteceptorConfig)
                .addPathPatterns("/**/*")
                .excludePathPatterns(excludeList);

        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
