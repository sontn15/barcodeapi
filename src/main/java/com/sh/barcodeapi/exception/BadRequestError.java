package com.sh.barcodeapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BadRequestError implements ResponseError {
    INVALID_INPUT(4000000, "Invalid input"),
    INVALID_CREATE_DATE(4000001, "createDate input must be not null"),
    INVALID_BILL_ID(4000002, "billId input must be not null"),
    BILL_ID_EXISTED(4000003, "billId input is existed"),
    LIST_ITEMS_IS_NULL(4000004, "list items input is null"),
    BILL_IS_NULL(4000005, "Bill input is null"),
    JSON_INCORRECT(4000006, "Json input incorrect"),
    PASSWORD_INCORRECT(4000011, "Password incorrect"),
    QUANTITY_DEVICE_STORE_UNAVAILABLE(4000014, "The quantity device of store unavailable"),
    STORE_STILL_HAD_EMPLOYEES(4000015, "The store is had employees"),
    ;

    private int errorCode;
    private String message;

    BadRequestError(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public int getStatus() {
        return HttpStatus.BAD_REQUEST.value();
    }
}
