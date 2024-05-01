package com.h87.sondji.utils;

import lombok.Builder;

@Builder
public record CreateNoteData(
        String title,
        String content
) {
}
