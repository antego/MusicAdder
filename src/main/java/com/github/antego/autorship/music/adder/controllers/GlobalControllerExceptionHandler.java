package com.github.antego.autorship.music.adder.controllers;

import com.github.antego.autorship.music.adder.models.exceptions.EtheriumConnectException;
import com.github.antego.autorship.music.adder.models.exceptions.FileDeserializableException;
import com.github.antego.autorship.music.adder.models.exceptions.IncorrectFileFormatException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(IncorrectFileFormatException.class)
    public ResponseEntity handleIncorrectFileFormat(IncorrectFileFormatException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(FileDeserializableException.class)
    public ResponseEntity handleFileDeserializable(FileDeserializableException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(EtheriumConnectException.class)
    public ResponseEntity handleEtheriumConnect(EtheriumConnectException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
