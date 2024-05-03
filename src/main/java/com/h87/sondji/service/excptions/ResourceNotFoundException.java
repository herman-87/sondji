package com.h87.sondji.service.excptions;

import com.h87.sondji.utils.ErrorCode;

public class ResourceNotFoundException extends Throwable {
    public ResourceNotFoundException(ErrorCode errorCode) {
        super(errorCode.getValue());
    }
}
