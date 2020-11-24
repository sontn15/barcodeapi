package com.sh.barcodeapi.exception;


public class ResponseException extends RuntimeException {
    private ResponseError responseError;


    public ResponseException(String message, ResponseError responseError) {
        super(message);
        this.responseError = responseError;
    }

    public ResponseError getResponseError() {
        return responseError;
    }

    public void setResponseError(ResponseError responseError) {
        this.responseError = responseError;
    }
}
