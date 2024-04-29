package com.h87.sondji.domain.tag;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class TagDescription {
    @Column(name = "c_description")
    private String value;
}
