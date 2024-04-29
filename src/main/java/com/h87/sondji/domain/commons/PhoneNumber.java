package com.h87.sondji.domain.commons;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PhoneNumber {
    @Column(name = "c_country_code")
    private String countryCode;
    @Column(name = "c_number")
    private String number;
}
