package com.ward.photogram.domain.image;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private int id;
    private String caption; //사진 넣을 때 설명
    private String postImageUrl; //사진을 전송받아서 그 사진을 서버 특정 폴더에 저장 -><DB 경로> Insert


    @JsonIgnoreProperties({"images"})
    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.EAGER) // 이미지를 select하면 조인해서 User정보를 같이 들고옴
    private User user; //1 : 1


    private LocalDateTime createDate;

    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", caption='" + caption + '\'' +
                ", postImageUrl='" + postImageUrl + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}