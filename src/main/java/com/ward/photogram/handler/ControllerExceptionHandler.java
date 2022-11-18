package com.ward.photogram.handler;


import com.ward.photogram.handler.ex.CustomValidationApiException;
import com.ward.photogram.handler.ex.CustomValidationException;
import com.ward.photogram.util.Script;
import com.ward.photogram.web.dto.CMRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController // 데이터 응답을 위해
@ControllerAdvice  // 모든 exception을 낚아채 처리함
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class) //해당 exception이 발생하면 처리합니다.
    public String validationException(CustomValidationException e){
    // CMRespDto,Script 비교
    // 1.클라이언트에게 응답할때는 script 좋음
    // 2. ajax 통신-cmresDto
    // 3.Android 통신-cmrespDto
        // -> 응답 받는 쪽이 클라이언트면 Script, 개발자면 CMResDto가 좋음
        if(e.getErrorMap() == null)
            return Script.back(e.getMessage());
        else
            return Script.back(e.getErrorMap().toString()); // => 오류메세지 팝업 및 자동으로 뒤로가짐
    }

    @ExceptionHandler(CustomValidationApiException.class)
    public ResponseEntity<?> validationException(CustomValidationApiException e){ //ResponseEntity<?>의 ?는 어떤 타입이든 추론해서 변경해준다.
        System.out.println("===============실행되나?==================");
        return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),null), HttpStatus.BAD_REQUEST);
    }
}
