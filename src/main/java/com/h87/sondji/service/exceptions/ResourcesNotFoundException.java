package com.h87.sondji.service.exceptions;

import com.h87.sondji.utils.SondjiErrorCode;

public class ResourcesNotFoundException extends Throwable {
    public ResourcesNotFoundException(SondjiErrorCode errorCode) {
        super(errorCode.getValue());
    }
}
