package com.ward.photogram.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ward.photogram.domain.user.User;
import com.ward.photogram.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service //IOC ,스프링컨테이너에 원래 있는 UserDetailsService를 덮어 씌워 대체함
public class PrincipalDetailService implements UserDetailsService {

    //실제 로그인 설정을 시큐리티 설정파일에 Post방식으로 넣어두면,
    //시큐리티 설정파일 <-Post 되면
    //IoC 컨테이너의 UserDetailsService가 로그인을 진행함
    //현재 PrincipalDetailsService implements UserDetailsService하여서 기존의
    //UserDetailsService가 PrincipalDetailsService로 덮어씌어져 해당 프로그램으로 작동되는 구조임
    private final UserRepository userRepository;
    // 1. 패스워드는 알아서 체킹하니까 신경쓸필요없다
    // 2. 리턴이 잘되면 자동으로 userdetails세션을 만든다
    @Override
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
        //로그인을 실행하면 이 함수가 실행됨
        //리턴이 잘되면 자동으로 UserDetails타입을 세션으로 만든다.
        //패스워드는 알아서 체킹하니깐 신경쓸 필요 없음

        User userEntity = userRepository.findByUsername(username);

        if(userEntity == null) {
            return null;
        }else {
            return new PrincipalDetails(userEntity);
        }


    }
    // * 스프링 시큐리티 로그인 프로세스 과정 *
    // 1) 시큐리티 설정파일에서 로그인 POST 요청을 계속 기다립니다.
    // 2) 누군가가 로그인을 시도하고 POST요청을 보내면 요청을 낚아챕니다.
    // 3) IoC컨테이너에서 UserDetailsService 라는 애가 정보를 받아 로그인을 진행합니다.
}
