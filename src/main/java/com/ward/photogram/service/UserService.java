package com.ward.photogram.service;

import com.ward.photogram.config.auth.PrincipalDetails;
import com.ward.photogram.domain.user.User;
import com.ward.photogram.domain.user.UserRepository;
import com.ward.photogram.handler.ex.CustomValidationApiException;
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
