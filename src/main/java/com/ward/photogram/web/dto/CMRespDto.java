package com.ward.photogram.web.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.SpringVersion;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CMRespDto<T>{
    private int code; //1(성공) -1(실패)
    private String message;
    private T data;
}
