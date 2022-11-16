package com.ward.photogram.handler;


import com.ward.photogram.handler.ex.CustomValidationApiException;
import com.ward.photogram.handler.ex.CustomValidationException;
import com.ward.photogram.util.Script;
import com.ward.photogram.web.dto.CMRespDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public String validationExcetion(CustomValidationException e){
    //CMRespDto,Script 비교
    // 1.클라이언트에게 응답할때는 script 좋음
    // 2. ajax 통신-cmresDto
    // 3.Android 통신-cmrespDto
    return Script.back(e.getErroMap().toString());
    }

    @ExceptionHandler(CustomValidationApiException.class)
    public CMRespDto<?> validationException(CustomValidationApiException e){
        return new CMRespDto(-1,e.getMessage(),e.getErroMap());
    }
}
