package com.h87.sondji.domain.note;

import com.h87.sondji.domain.commons.EntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class NoteTitle extends EntityBase {
    @Column(name = "c_title")
    private String value;
}
