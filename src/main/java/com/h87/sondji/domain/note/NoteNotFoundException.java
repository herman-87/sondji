package com.h87.sondji.domain.note;

import com.h87.sondji.utils.SondjiErrorCode;

public class NoteNotFoundException extends Throwable {
    public NoteNotFoundException(SondjiErrorCode errorCode) {
        super(errorCode.getValue());
    }
}
