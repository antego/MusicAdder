package com.github.antego.autorship.music.adder.controllers;

import com.github.antego.autorship.music.adder.models.exceptions.ErrorResource;
import com.github.antego.autorship.music.adder.models.exceptions.EtheriumConnectException;
import com.github.antego.autorship.music.adder.models.exceptions.FileDeserializableException;
import com.github.antego.autorship.music.adder.models.exceptions.IncorrectFileFormatException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            IncorrectFileFormatException.class,
            FileDeserializableException.class,
            EtheriumConnectException.class
    })
    public ResponseEntity<Object> handleException(RuntimeException e, WebRequest request) {
        ErrorResource resource = new ErrorResource("Invalid Request", e.getMessage());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(e, e.getMessage(), headers, HttpStatus.BAD_REQUEST, request);
    }
}
