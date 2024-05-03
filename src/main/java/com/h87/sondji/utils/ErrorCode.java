package com.h87.sondji.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    NOTE_NOT_FOUND("Note Not Found");

    private final String value;
}
