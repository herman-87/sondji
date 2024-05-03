package com.h87.sondji.commons;

import com.h87.sondji.service.excptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ResourcesAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ResourceNotFoundException.class})
    public @ResponseBody String handleException(ResourceNotFoundException exception) {
        return exception.getMessage();
    }
}
