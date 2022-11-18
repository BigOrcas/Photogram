package com.ward.photogram.config.auth;

import com.ward.photogram.domain.user.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
@Data
public class PrincipalDetails implements UserDetails {

    private static final long serialVersionUID=1L;

    private User user;

    public PrincipalDetails(User user){
        this.user =user;
    }
    // 권한 : 한개가 아닐수있음(3개 이상의 권한)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //권한

        Collection<GrantedAuthority> collector = new ArrayList<>();
        collector.add(() -> {return user.getRole();});

//        collector.add(new GrantedAuthority() {
//            @Override
//            public String getAuthority() {
//                return user.getRole();
//            }
//        });

        //return collector; //권한이 (한개가 아닐 수 있음)
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override  //이 계정이 만료가 되었니? , 아래 함수들 다 false이면 로그인 안됨
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override //이 계정이 잠겼는지?
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override //이 비밀번호..?
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
