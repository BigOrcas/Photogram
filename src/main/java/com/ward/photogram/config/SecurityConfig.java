package com.ward.photogram.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.ward.photogram.config.oauth.OAuth2DetailsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity//해당 파일로 시큐리티를 활성화
@Configuration //가시적으로 설정파일이야 ~ , Bean 등록할꺼야 ~ 라는건 알수있다. Bean을 등록할때 싱글톤(singleton)이 되도록 보장해준다. 스프링컨테이너에서 Bean을 관리할수있게 됨.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final OAuth2DetailsService oAuth2DetailsService;

    @Bean //개발자가 직접 제어가 불가능한 외부 라이브러리 등을 Bean로 만들려할 때 사용된다.
    public BCryptPasswordEncoder encode(){
        return new BCryptPasswordEncoder();
    }  // 무언가를 암호화하는데 사용할수있는 메소드를 가진 클래스
    @Override //상속받은 메서드를 재정의할 때 메서드 이름이 틀리지 않게 쓰기 위한 어노테이션
    protected void configure(HttpSecurity http) throws Exception {  //HttpSecurity 클래스를 통해 HTTP 요청에 대한 보안을 설정할 수 있습니다.
        http.csrf().disable(); // csrf 토큰 비활성화시킨다 default는 활성
        http.authorizeRequests() // HttpServletRequest 요청 URL에 따라 접근 권한을 설정합니다.
                .antMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**","/api/**") //이 url을 가진 주소들은
                .authenticated() // 인증이 필요하다
                .anyRequest() // 나머지 요청들은
                .permitAll() // 다 허용합니다!
                .and()
                .formLogin() // 인증 로그인이 필요하다
                .loginPage("/auth/signin") //로그인이 필요한 url을 타면 이쪽으로 가게하겠다. GET방식
                .loginProcessingUrl("/login") //POST -> 스프링 시큐리티가 로그인 프로세스 진행 (로그인 요청인지 확인)//UserDetailsService
                .defaultSuccessUrl("/") //로그인을 정상척으로 처리하면 default url로 가게함
                .and()
                .oauth2Login()// form 로그인도 하는데 ,oauth2로그인도 할거야
                .userInfoEndpoint()// oauth2로그인을 하면 최종응답을 회원정보를 바로 받을 수 있다.
                .userService(oAuth2DetailsService);
    }
}
