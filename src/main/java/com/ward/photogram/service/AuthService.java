package com.ward.photogram.service;

import com.ward.photogram.domain.user.User;
import com.ward.photogram.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor  // final 키워드가 붙은 필드를 초기화해주는 생성자를 자동으로 만들어줍니다. DI에 사용됩니다. 스프링 컨테이너에서 적절한 타입을 찾아 자동으로 주입해줍니다.
@Service // 1.IoC 2. 트랜잭션 관리
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder; //비밀번호 암호화

    @Transactional  // Write(Insert, Update, Delete) 할 때는 트랜잭션 처리
    public User 회원가입(User user){
        //회원가입 진행
        String rawPassword =user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);  //패스워드 암호화 됨
        user.setPassword(encPassword);
        user.setRole("ROLE_USER"); // 권한 부여, 관리자 : ROLE_ADMIN

        User userEntity= userRepository.save(user); //데이터베이스에 저장하고 저장한 객체 반환
        return userEntity;
    }
}
