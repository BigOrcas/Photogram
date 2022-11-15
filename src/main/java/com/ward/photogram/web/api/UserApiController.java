package com.ward.photogram.web.api;

import com.ward.photogram.config.auth.PrincipalDetails;
import com.ward.photogram.domain.user.User;
import com.ward.photogram.service.UserService;
import com.ward.photogram.web.dto.CMRespDto;
import com.ward.photogram.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/user/{id}")
    public CMRespDto<?> update(@PathVariable int id, UserUpdateDto userUpdateDto, @AuthenticationPrincipal PrincipalDetails principalDetails){
        User userEntity = userService.회원수정(id,userUpdateDto.toEntity());
        principalDetails.setUser(userEntity);
        return new CMRespDto<>(1,"회원수정완료",userEntity);

    }
}
