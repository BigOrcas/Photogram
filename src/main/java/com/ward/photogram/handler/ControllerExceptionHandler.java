package com.ward.photogram.handler;


import com.ward.photogram.handler.ex.CustomValidationException;
import com.ward.photogram.web.dto.CMRespDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public CMRespDto<?> validationException(CustomValidationException e){
        return new CMRespDto(-1,e.getMessage(),e.getErroMap());
    }
}
