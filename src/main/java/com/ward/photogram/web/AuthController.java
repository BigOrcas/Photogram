package com.ward.photogram.web;

import com.ward.photogram.domain.user.User;
import com.ward.photogram.handler.ex.CustomValidationExcetion;
import com.ward.photogram.service.AuthService;
import com.ward.photogram.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor // final 필드를 DI할때 사용
@Controller //1. Ioc 2. 파일을 리턴하는 컨트롤러
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);


    private final AuthService authService;

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
    @PostMapping("/auth/signup")//responsebody 데이터를 return한다
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {
        //유효성검사가 하나라도 실패한다면 bindinResult에 담긴다
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage()); //에러가 발생한 아이디와 메세지를 hash맵에 저장
                //System.out.println(error.getDefaultMessage());
            }
            throw new CustomValidationExcetion("유효성검사오류남",errorMap);
        } else {
            //User<- SignupDto
            User user = signupDto.toEntity();
            User userEntity = authService.회원가입(user);
            System.out.println(userEntity);
            return "auth/signin";
        }
    }
    //시큐리티는 csrf 토큰 검사를 통해 클라이언트가 요청한 메세지를 먼저 검사한다
    //input tag에 csrf가 붙어서 요청이들어옴
    //csrf 토큰이 있나? 확인을 한뒤 있다면 서버를 동작시킨다
    //정상적인 요청인가 확인하는게 csrf 토큰
}
