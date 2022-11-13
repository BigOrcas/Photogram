package com.ward.photogram.config.auth;

import com.ward.photogram.domain.user.User;
import com.ward.photogram.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service //IOC
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
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException{

        User userEntity = userRepository.findByUsername(username);

        if(userEntity ==null){
            return null;
        }else
            return new PrincipalDetails(userEntity); //세션에 저장되는 애


    }
}
