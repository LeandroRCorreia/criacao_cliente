package com.orbitaltech.demo.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionHandlerDto {

    private HttpStatus statusCode;
    private String messageCode;

}
