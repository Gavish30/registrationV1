package com.api.exceptions;

import com.api.payload.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionalHandler {
    @ExceptionHandler(ResourecNotFoundException.class)
    public ResponseEntity<ErrorDto> resourceNotFound(WebRequest request,
                                                     ResourecNotFoundException r ){
        ErrorDto error = new ErrorDto(r.getMessage(), new Date(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR );
    }
    @ExceptionHandler(Exception.class)
    public  ResponseEntity<String> handleGlobalException(
        Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
