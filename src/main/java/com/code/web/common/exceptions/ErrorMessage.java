package com.code.web.common.exceptions;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class ErrorMessage {
    private String message;
    private String exeption;
    private String path;
    private Map<String, String> errors;

  public ErrorMessage(String message, String exeption, String path ) {
        this.message = message;
        this.exeption = exeption;
        this.path = path;
        this.errors = new HashMap<>();
    }

    public ErrorMessage(String message, String exeption, String path, Map<String,String> errors) {
        this.message = message;
        this.exeption = exeption;
        this.path = path;
        this.errors = errors;
    }

}
