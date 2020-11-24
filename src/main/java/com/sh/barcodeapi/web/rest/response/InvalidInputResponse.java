package com.sh.barcodeapi.web.rest.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class InvalidInputResponse extends ErrorResponse {

    private Set<FieldErrorResponse> errors;

    public InvalidInputResponse(String message, String errorName, Set<FieldErrorResponse> errors) {
        super(message, errorName);
        this.errors = errors;
    }
}
