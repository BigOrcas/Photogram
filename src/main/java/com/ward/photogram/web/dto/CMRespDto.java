package com.ward.photogram.web.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.SpringVersion;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CMRespDto<T>{ //제네릭을 사용하는 이유는 다른 페이지에서도 사용 할 수 있도록 제작
    private int code; //1(성공) -1(실패)
    private String message;
    private T data;
}
