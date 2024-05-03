package com.h87.sondji.commons;

import com.h87.sondji.service.excptions.ResourcesNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ResourcesAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ResourcesNotFoundException.class})
    public @ResponseBody String handleException(ResourcesNotFoundException exception) {
        return exception.getMessage();
    }
}
