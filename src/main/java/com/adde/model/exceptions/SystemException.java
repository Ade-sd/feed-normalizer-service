package com.adde.model.exceptions;

import com.adde.model.enums.SystemError;
import lombok.Getter;

import java.util.List;

@Getter
public class SystemException extends RuntimeException {
    private final SystemError error;
    private final Integer errorCode;
    private final Object argument;
    private final List<ErrorResult> errorResults;

    public SystemException(SystemError error, Object argument, Integer errorCode) {
        this.error = error;
        this.argument = argument;
        this.errorCode = errorCode;
        this.errorResults = null;
    }

    public SystemException(SystemError error, Object argument, Integer errorCode, List<ErrorResult> errorResults) {
        this.error = error;
        this.argument = argument;
        this.errorCode = errorCode;
        this.errorResults = errorResults;
    }
}