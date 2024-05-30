package com.h87.sondji.commons;

import com.h87.sondji.domain.note.NoteNotFoundException;
import com.h87.sondji.domain.tag.TagNotFoundException;
import com.h87.sondji.exceptions.ResourcesNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourcesDomainAdvice {

    @ExceptionHandler({TagNotFoundException.class, NoteNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(ResourcesNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}
