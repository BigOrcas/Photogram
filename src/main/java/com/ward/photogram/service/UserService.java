package com.ward.photogram.service;

import com.ward.photogram.config.auth.PrincipalDetails;
import com.ward.photogram.domain.user.User;
import com.ward.photogram.domain.user.UserRepository;
import com.ward.photogram.handler.ex.CustomException;
import com.ward.photogram.handler.ex.CustomValidationApiException;
import com.ward.photogram.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    // ex) 10번 유저 회원정보 수정해 -> DB에 10번 유저가 없을 시 -> 예외처리
    // 레파지토리에서 요청받은 아이디의 회원을 찾고 없을 시 예외를 발생시킵니다.
    // .orElserThrow가 반환값이 null이면 예외를 던지는 함수입니다.
    // 예외가 발생하면 따로 구현해놓은 핸들러가 동작해 처리하게 됩니다.

    public UserProfileDto  회원프로필(int pageUserId,int principalId){
        UserProfileDto dto = new UserProfileDto();

        User userEntity =userRepository.findById(pageUserId).orElseThrow(()->{
           throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
        });

        dto.setUser(userEntity);
        dto.setPageOwnerState(pageUserId == principalId);
        dto.setImageCount(userEntity.getImages().size());

       return dto;
    }
    @Transactional
    public User 회원수정(int id, User user){
        // 1.영속화
        //User userEntity = userRepository.findById(id).get();
        User userEntity = userRepository.findById(id).orElseThrow(() -> {return new CustomValidationApiException("찾을수없는 id입니다");});
        // 1.무조건 찾았다. 걱정마 get() 2.못찾았어 익센션 발동시킬게 orElseThrow
        // 2. 영속화된 오브젝트를 수정 - 더티체킹(업데이트완료)
        userEntity.setName(user.getName());
        String rawPassword =user.getPassword();
        String encPassword=bCryptPasswordEncoder.encode(rawPassword);
        userEntity.setPassword(encPassword);
        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());
     return userEntity;
    }//더티체킹이 일어나서 업데이트완료
}
