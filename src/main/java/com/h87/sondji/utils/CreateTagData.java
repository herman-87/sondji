package com.h87.sondji.utils;

import lombok.Builder;

@Builder
public record CreateTagData(
        String name,
        String description
) {
}
