package com.ward.photogram.web.api;

import com.ward.photogram.config.auth.PrincipalDetails;
import com.ward.photogram.domain.user.User;
import com.ward.photogram.handler.ex.CustomValidationApiException;
import com.ward.photogram.handler.ex.CustomValidationException;
import com.ward.photogram.service.UserService;
import com.ward.photogram.web.dto.CMRespDto;
import com.ward.photogram.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/user/{id}")
    public CMRespDto<?> update(
            @PathVariable int id,
            @Valid UserUpdateDto userUpdateDto,
            BindingResult bindingResult,//꼭 @Vaild가 적혀있는 다음파라미터에 적어야함
            @AuthenticationPrincipal PrincipalDetails principalDetails){

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage()); //에러가 발생한 아이디와 메세지를 hash맵에 저장
                // System.out.println("=================");
                // System.out.println(error.getDefaultMessage()); error message 주석
            }
            throw new CustomValidationApiException("유효성 검사 실패함", errorMap);// -> handler -> CotrollerExcoptionHandler
        }else{
            User userEntity = userService.회원수정(id,userUpdateDto.toEntity());
            principalDetails.setUser(userEntity);
            return new CMRespDto<>(1,"회원수정완료",userEntity);
        }
    }
}
