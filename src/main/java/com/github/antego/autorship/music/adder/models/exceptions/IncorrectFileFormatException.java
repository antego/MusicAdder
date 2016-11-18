package com.github.antego.autorship.music.adder.models.exceptions;

public class IncorrectFileFormatException extends RuntimeException {

    public IncorrectFileFormatException() {
    }

    public IncorrectFileFormatException(String message) {
        super(message);
    }
}
