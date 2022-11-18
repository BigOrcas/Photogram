package com.ward.photogram.web.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.SpringVersion;

import java.util.Map;
// 개발자가 응답을 해야하는 경우 DTO를 사용하는게 효율적일 때가 있기 때문에 만들어준다.
// 공통 응답 DTO
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CMRespDto<T>{ //제네릭을 사용하는 이유는 다른 페이지에서도 사용 할 수 있도록 제작(다른 객체나 String..)
    private int code; //1(성공) -1(실패)
    private String message;
    private T data;

}
