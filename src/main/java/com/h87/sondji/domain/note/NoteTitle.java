package com.h87.sondji.domain.note;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class NoteTitle {
    @Column(name = "c_title")
    private String value;
}
