package com.orbitaltech.demo.presentation.controller.handlers;

import com.orbitaltech.demo.presentation.dto.ExceptionHandlerDto;
import com.orbitaltech.demo.service.exception.ClienteNaoEncontradoException;
import com.orbitaltech.demo.service.exception.EnderecoNaoEncontradoException;
import com.orbitaltech.demo.service.exception.FormatoEnderecoInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<ExceptionHandlerDto> clienteNaoEncontradoException(ClienteNaoEncontradoException ex) {
        ExceptionHandlerDto exceptionHandlerDTO = new ExceptionHandlerDto(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionHandlerDTO);
    }

    @ExceptionHandler(EnderecoNaoEncontradoException.class)
    public ResponseEntity<ExceptionHandlerDto> enderecoNaoEncontradoException(EnderecoNaoEncontradoException ex){
        ExceptionHandlerDto exceptionHandlerDTO = new ExceptionHandlerDto(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionHandlerDTO);
    }

    @ExceptionHandler(FormatoEnderecoInvalidoException.class)
    public ResponseEntity<ExceptionHandlerDto> formatadoEnderecoInvalido(FormatoEnderecoInvalidoException ex){
        ExceptionHandlerDto exceptionHandlerDTO = new ExceptionHandlerDto(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionHandlerDTO);
    }

}
