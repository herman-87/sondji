package com.h87.sondji.service.excptions;

import com.h87.sondji.utils.ErrorCode;

public class ResourcesNotFoundException extends Throwable {
    public ResourcesNotFoundException(ErrorCode errorCode) {
        super(errorCode.getValue());
    }
}
