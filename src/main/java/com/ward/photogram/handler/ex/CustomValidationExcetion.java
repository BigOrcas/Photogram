package com.ward.photogram.handler.ex;

import java.util.Map;

public class CustomValidationExcetion extends RuntimeException{


    //객체를 구분할때!!
    private static final long serialVersionUID=1L;

    private Map<String,String> erroMap;

    public CustomValidationExcetion(String message,Map<String,String> erroMap){
        super(message);
        this.erroMap=erroMap;
    }
    public Map<String,String>getErroMap(){
        return erroMap;
    }
}
