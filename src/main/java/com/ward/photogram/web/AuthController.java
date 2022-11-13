
package com.ward.photogram.web;

import com.ward.photogram.domain.user.User;
import com.ward.photogram.handler.ex.CustomValidationException;
import com.ward.photogram.service.AuthService;
import com.ward.photogram.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor//Ioc에 등록되려면 생성자가 있어야하는데
//만약 AuthService에서 @Service를 하지않으면 IoC 컨테이너이 등록되지않음
//해당 생성자를 자동으로 관리해주는 lombok
@Controller //IoC등록 //파일을 리턴하는 컨트롤러
public class AuthController {


    private static final Logger log = LoggerFactory.getLogger(AuthController.class);


    private final AuthService authService;

    @GetMapping("/auth/signin")
    public String signinForm() {
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm() { //key=valie x-www-form-urlencoded
        return "auth/signup";
    }
    //회원가입버튼 -> /auth/signUp -> auth/signin


    @PostMapping("/auth/signup")
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {
        // String 앞에 @ResponseBody 사용 시 String(파일)이아니라 데이터를 리턴함
        //Valid 유효성 검사를 진행하겠다는것
        //Valid로 유효성 검사를 진행한 객체가 오류가 발생하는 경우 BindingResult가 에러를 .getFieldErrors 컬렉션에 모두 모아줌

        //회원가입이 성공하면 로그인 페이지로 가면되니까

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage()); //에러가 발생한 아이디와 메세지를 hash맵에 저장
//                System.out.println("=================");
//                System.out.println(error.getDefaultMessage()); error message 주석
            }
            throw new CustomValidationException("유효성 검사 실패함", errorMap);// -> handler -> CotrollerExcoptionHandler
        } else {

            if (signupDto.getUsername().length() > 20) {
                log.info("길이초과");
            }
//        log.info(signupDto.toString());
            User user = signupDto.toEntity();
//        log.info(user.toString());
            User userEntity = authService.회원가입(user);

            //User <- signUpDto dat
            return "auth/signin";
        }
    }


}
