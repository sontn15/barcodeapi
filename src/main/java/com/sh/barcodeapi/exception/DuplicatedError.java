package com.sh.barcodeapi.exception;

import org.springframework.http.HttpStatus;

public enum DuplicatedError implements ResponseError {
    DUPLICATED_RESOURCE_INFO("Duplicated unique resource information"),
    ;

    DuplicatedError(String message) {
        this.message = message;
    }

    private String message;

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getStatus() {
        return HttpStatus.CONFLICT.value();
    }
}
