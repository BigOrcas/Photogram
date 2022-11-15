package com.ward.photogram.web.dto.user;

import com.ward.photogram.domain.user.User;
import lombok.Data;
import org.springframework.core.SpringVersion;

@Data
public class UserUpdateDto {
    private String name;
    private String password;
    private String website;
    private String bio;
    private String phone;
    private String gender;

    // 조금 위험 코드 수정이 필요
    public User toEntity(){
        return User.builder()
                .password(password)
                .name(name)
                .website(website)
                .bio(bio)
                .phone(phone)
                .gender(gender)
                .build();
    }

}
