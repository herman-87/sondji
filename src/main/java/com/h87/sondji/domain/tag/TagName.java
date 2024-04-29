package com.h87.sondji.domain.tag;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class TagName {
    @Column(name = "c_name")
    private String value;
}
