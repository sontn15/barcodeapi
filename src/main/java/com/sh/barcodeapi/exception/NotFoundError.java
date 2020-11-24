package com.sh.barcodeapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum NotFoundError implements ResponseError {
    STORE_NOT_FOUND(4040002, "Store not found"),
    ITEM_NOT_FOUND(4040004, "Item not found"),
    BILL_NOT_FOUND(4040005, "Bill not found"),
    ;

    private int errorCode;
    private String message;

    NotFoundError(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    @Override
    public String getName() {
        return this.name();
    }

    public int getStatus() {
        return HttpStatus.NOT_FOUND.value();
    }

    @Override
    public Integer getCode() {
        return errorCode;
    }
}
