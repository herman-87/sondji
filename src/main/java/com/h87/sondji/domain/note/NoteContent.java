package com.h87.sondji.domain.note;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class NoteContent {
    @Column(name = "c_content")
    private String value;
}
