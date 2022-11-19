package com.ward.photogram.web;

import com.ward.photogram.config.auth.PrincipalDetails;
import com.ward.photogram.domain.user.User;
import com.ward.photogram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{id}")
    public String profile(@PathVariable int id, Model model){
        User userEntity = userService.회원프로필(id);
        model.addAttribute("images",userEntity);
        return "user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model){

        // 세션 정보 확인하기
        // UserDetailsService 클래스의 loadUserByUsername 함수가 성공적으로 반환되면 새로운 세션을 생성한다.
        // 이것을 개발자가 직접 확인하는 방법을 정리하자면
        // 새로운 세션이 생성되면 SecurityContextHolder라는 클래스 안에 정보가 저장된다.
        // 새롭게 생성한 User 정보가 들어있는 PrincipalDetails 객체를 반환하기 때문에 Principal에 해당 객체가 저장됩니다.
        // 순서는  SecurityContextHolder -> SecurityContext -> Authentication -> Principal
        // @AuthenticationPrincipal 어노테이션을 사용하면 Authentication 객체에 바로 접근할 수 있어 편리합니다.

        // 세션에 접근하는 두가지 방법

        // 1.추천
        System.out.println("세션정보 :" + principalDetails.getUser());
        
        // 2. 극혐
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        PrincipalDetails mPrincipalDetails =(PrincipalDetails)auth.getPrincipal();
//        System.out.println("직접 찾은 세션정보 :" +mPrincipalDetails.getUser());

        model.addAttribute("pricipal",principalDetails.getUser());
        return "user/update";
    }

}
