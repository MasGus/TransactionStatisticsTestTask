package com.n26.controller.exception.advice;

import com.n26.controller.exception.TimestampFormatException;
import com.n26.controller.exception.TimestampFutureException;
import com.n26.controller.exception.TimestampPastException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class InvalidTimestampAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(TimestampFutureException.class)
    String handleFutureDate(TimestampFutureException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(TimestampPastException.class)
    String handleOldDate(TimestampPastException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(TimestampFormatException.class)
    String handleTimestampFormat(TimestampFormatException ex) {
        return ex.getMessage();
    }
}
