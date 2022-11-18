package com.ward.photogram.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

    @Modifying //Insert,delete,update를 네이티브 쿼리로 작성하려면 해당 어노테이션 필요!!
    @Query(value="INSERT INTO subscribe(fromUserId,toUserId,createDate) VALUES(:fromUserId:toUserId new())",nativeQuery = true)
    void mSubscribe(int fromUserId,int toUserId);

    @Modifying
    @Query(value = "DELETE FROM subscibe WHERE fromUserId=:fromUserId and toUserId=:toUserId",nativeQuery = true)
    void mUnSubscribe(int fromUserId,int toUserId);

    //return 타입이 int이면 : 성공하면 변경된 행 개수, 실패하면 -1 반환됨
    // -> 변경된 행의 개수만큼 숫자를 return함, ex) 행 10개 바꾸면 10 return, 0 return 하면 변경된게 없다는 뜻
    // void 해도 상관없음

    // 구독하는 아이디와 구독받는 아이디를 갖고 일일이 Subscribe 객체를 만들어
    // 레퍼지토리에 저장하는 것보단 JPA Native SQL Query를 사용해 쿼리문으로 바로 디비에 저장하는 방법이 간편할 것이다.
    // Native SQL Query ? JPA는 Native SQL을 통해 SQL을 직접 사용할 수 있는 기능을 제공한다.
    // - SQL을 개발자가 직접 정의할 수 있다.
    // - Native SQL 사용 시 엔티티를 조회하고, JPA가 지원하는 영속성 컨텍스트의 기능을 그대로 사용 가능합니다.

}