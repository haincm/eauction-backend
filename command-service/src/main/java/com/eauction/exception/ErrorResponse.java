package com.eauction.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ErrorResponse {

    private int errorCode;
    private String error;
    private String errorMessage;
    private List<String> fieldErrors = new ArrayList<>();
    public ErrorResponse(HttpStatus status, String message, List<String> fieldErrors ) {
        this.errorCode = status.value();
        this.error = status.name();
        this.errorMessage = message;
        this.fieldErrors = fieldErrors;
    }


}
