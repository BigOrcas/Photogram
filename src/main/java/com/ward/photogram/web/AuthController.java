package com.ward.photogram.web;

import com.ward.photogram.web.dto.auth.SignupDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller //1. Ioc 2. 파일을 리턴하는 컨트롤러
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/auth/signin")
    public String signinForm(){
        return "auth/signin";
    }
    @GetMapping("/auth/signup")
    public String signupForm(){
        return "auth/signup";
    }
    //회원가입버튼 -> /auth/signup -> /auth/signin
    //회원가입버튼 x
    @PostMapping("/auth/signup")
    public String signup(SignupDto signupDto){
        log.info(signupDto.toString());
        return "auth/signin";
    }
    //시큐리티는 csrf 토큰 검사를 통해 클라이언트가 요청한 메세지를 먼저 검사한다
    //input tag에 csrf가 붙어서 요청이들어옴
    //csrf 토큰이 있나? 확인을 한뒤 있다면 서버를 동작시킨다
    //정상적인 요청인가 확인하는게 csrf 토큰
}
