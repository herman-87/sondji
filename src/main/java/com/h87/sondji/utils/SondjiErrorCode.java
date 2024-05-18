package com.h87.sondji.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SondjiErrorCode {
    NOTE_NOT_FOUND("Note Not Found"),
    SOMETHING_WRONG("Something wrong");

    private final String value;
}
