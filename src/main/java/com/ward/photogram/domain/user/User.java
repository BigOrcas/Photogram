package com.ward.photogram.domain.user;

//JPA-Java Persistence API(자바를 데이터를 영구적으로 저장(DB)할수있는 API를 제공)

import com.ward.photogram.domain.image.Image;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

//DB에 저장하기 위한 모델
//JPA = Java persistence API (자바로 데이터를 영구적으로 저장(DB)할 수 있는 API를 제공)

@ToString
@Builder // builder()함수를 사용해 해당 객체로 초기화 할 수 있게 해주는 Lombok API 입니다.
@AllArgsConstructor  // 모든 필드 값을 파라미터로 받는 생성자를 만듦
@NoArgsConstructor //파라미터가 없는 기본 생성자를 생성
@Data   // Getter와 Setter를 자동으로 생성해줍니다.
@Entity // 디비에 테이블을 생성
public class User {
    @Id // primary Key(기본키) 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //값을 자동으로 생성하고 번호 증략 전략을 데이터베이스를 따라간다.
    private int id; //나중에 사용자가 많아지게 되면 Long을 써야함


    @Column(length = 15, unique = true) //제약조건 DB에 들어갈 때, 길이 제한 및 Unique
    private String username;

    @Column(nullable = false) //빈 데이터 못들어오게 막음
    private String password;

    @Column(nullable = false) //빈 데이터 못들어오게 막음
    private String email;

    @Column(nullable = false) //빈 데이터 못들어오게 막음
    private String name;

    private String website; //웹사이트
    private String bio; //자기소개
    private String phone;
    private String gender;

    private String profileImageUrl; //유저 사진
    private String role; //권한

    // 나는 연관관계의 주인이 아니다 그러므로 테이블에 칼럼을 만들지마
    // User를 select할때 해당 USER ID로 등록된 IMAGE를 다가져와
    // Lazy= user를 selec할때 해당 user id로 등록된 image들을 전부 join해서 가져와!!
    // Eager = user를 select할때 해당 user ㅑid 로 등록된 image들을 전부 join해서 가져와
    @OneToMany(mappedBy="user",fetch =FetchType.LAZY)
    @ToString.Exclude
    private List<Image> images; //양방향 매핑


    private LocalDateTime createDate;

    @PrePersist //디비에 INSERT 되기 직전에 실행
    public void createDate(){
        this.createDate =LocalDateTime.now();
    }

}
