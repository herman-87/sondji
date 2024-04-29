package com.h87.sondji.domain.commons;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class EmailAddress {
    @Column(name = "c_email")
    private String value;
}
