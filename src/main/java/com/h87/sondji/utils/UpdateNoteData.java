package com.h87.sondji.utils;

import lombok.Builder;

@Builder
public record UpdateNoteData(
        String title,
        String content
) {
}
