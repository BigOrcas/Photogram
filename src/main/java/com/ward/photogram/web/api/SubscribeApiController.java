package com.ward.photogram.web.api;


import com.ward.photogram.config.auth.PrincipalDetails;
import com.ward.photogram.service.SubscribeService;
import com.ward.photogram.web.dto.CMRespDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SubscribeApiController {

    private final SubscribeService subscribeService;

    @PostMapping("/api/subscribe/{toUserId}")
    public ResponseEntity<?> subscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int toUserId){
        subscribeService.구독하기(principalDetails.getUser().getId(), toUserId);
        System.out.println("여기냐?");
        return new ResponseEntity<>(new CMRespDto<>(1, "구독하기 성공", null), HttpStatus.OK);
    }

    @DeleteMapping("/api/subscribe/{toUserId}")
    public ResponseEntity<?> unSubscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int toUserId){
        subscribeService.구독취소하기(principalDetails.getUser().getId(), toUserId);
        return new ResponseEntity<>(new CMRespDto<>(1, "구독취소하기 성공", null), HttpStatus.OK);
    }
    // 컨트롤러에서 Post 요청을 받을 때 다음과 같이 현재 세션의 유저 아이디와 구독할 유저의 아이디를 넘겨주면
    //서비스에서 레퍼지토리의 앞서 작성한 네이티브 쿼리 함수를 실행하면 됩니다.
    // 그러면 작성했던 쿼리문이 실행되어 데이터베이스 Subscribe 테이블에 레코드가 추가됩니다.
}
