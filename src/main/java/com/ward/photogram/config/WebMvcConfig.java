package com.ward.photogram.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer { // web 설정 파일

    @Value("${file.path}")
    private String uploadFolder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        registry
                .addResourceHandler("/upload/**") //jsp페이지에서 /upload/** 주소패턴이 나오면 작동
                .addResourceLocations("file:///" + uploadFolder) //properties 에 해당하는 주소
                .setCachePeriod(60 * 10 * 6) //1시간
                .resourceChain(true) //를 걸어주면 발동
                .addResolver(new PathResourceResolver());
    }


}