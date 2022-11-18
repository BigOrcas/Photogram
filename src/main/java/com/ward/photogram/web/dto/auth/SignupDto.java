package com.ward.photogram.web.dto.auth;

import com.ward.photogram.domain.user.User;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data //Getter,Setter
public class SignupDto {

    //Dto : data transfer object , 통신을 위해 데이터를 담아두는 객체
    @Size(min=2,max=20)
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    @NotBlank
    private String name;

    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .name(name)
                .build();

    }
   }
