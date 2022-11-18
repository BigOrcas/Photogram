package com.ward.photogram.domain.image;

import com.ward.photogram.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
public class Image { // (image)N : (user)1 한명의 유저는 여러개의 이미지를 만들 수 있음

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String caption; //사진 넣을 때 설명
    private String postImageUrl; //사진을 전송받아서 그 사진을 서버 특정 폴더에 저장 -><DB 경로> Insert

    @JoinColumn(name = "userId")
    @ManyToOne
    private User user; //1 : 1


    private LocalDateTime createDate;

    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

}