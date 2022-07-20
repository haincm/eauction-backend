package com.eauction.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductCategoryException extends RuntimeException
{
    public ProductCategoryException(String exception) {
        super(exception);
    }
}