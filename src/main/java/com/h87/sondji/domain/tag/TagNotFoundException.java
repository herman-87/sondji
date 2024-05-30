package com.h87.sondji.domain.tag;

import com.h87.sondji.utils.SondjiErrorCode;

public class TagNotFoundException extends Throwable {
    public TagNotFoundException(SondjiErrorCode errorCode) {
        super(errorCode.getValue());
    }
}
