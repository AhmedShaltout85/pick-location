package com.a08r.pick_location.errors;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

//@ControllerAdvice
@RestControllerAdvice
public class ExceptionHandlers extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
//    @Bean
//    @RequestMapping


    public final ResponseEntity<ErrorResponse> handleItemNotFoundException
            (final RecordNotFoundException recordNotFoundException){
        ErrorResponse errorResponse = new ErrorResponse("404",
                recordNotFoundException.getMessage(), new Date());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    //handle other exceptions
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
//    @Bean
//    @RequestMapping
    public final ResponseEntity<ErrorResponse> handleOtherException
    (final Exception generalException){
        ErrorResponse errorResponse = new ErrorResponse("400",
                generalException.getMessage(), new Date());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
