package com.orbitaltech.demo.controller.handlers;

import com.orbitaltech.demo.dto.ExceptionDto;
import com.orbitaltech.demo.exception.ClienteNaoEncontradoException;
import com.orbitaltech.demo.exception.EnderecoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<ExceptionDto> clienteNaoEncontradoException(ClienteNaoEncontradoException ex) {
        ExceptionDto exceptionDTO = new ExceptionDto(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }

    @ExceptionHandler(EnderecoNaoEncontradoException.class)
    public ResponseEntity<ExceptionDto> enderecoNaoEncontradoException(EnderecoNaoEncontradoException ex){
        ExceptionDto exceptionDTO = new ExceptionDto(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }




}
