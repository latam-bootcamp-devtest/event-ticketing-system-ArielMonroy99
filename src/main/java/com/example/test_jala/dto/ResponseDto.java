package com.example.test_jala.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {
    private Long timestamp;
    private String message;
    private Integer status;
}
