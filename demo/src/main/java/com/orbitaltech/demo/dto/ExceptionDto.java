package com.orbitaltech.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDto {

    private HttpStatus statusCode;
    private String messageCode;

}
