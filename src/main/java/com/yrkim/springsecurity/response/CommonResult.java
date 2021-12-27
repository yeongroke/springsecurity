package com.yrkim.springsecurity.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CommonResult {
    
    // @ApiModelProperty : 모델의 요소에 설명을 추가 (swagger)
    @ApiModelProperty(value = "응답 코드 번호")
    private int code;
    @ApiModelProperty(value = "응답 메시지")
    private String msg;
}
