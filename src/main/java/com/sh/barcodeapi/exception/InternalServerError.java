package com.sh.barcodeapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum InternalServerError implements ResponseError {
    INTERNAL_SERVER_ERROR(5000000, "Internal server error"),
    ;

    private int errorCode;
    private String message;

    InternalServerError(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    @Override
    public String getName() {
        return this.name();
    }

    public int getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    @Override
    public Integer getCode() {
        return errorCode;
    }
}
