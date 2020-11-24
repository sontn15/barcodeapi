package com.sh.barcodeapi.web.rest.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FieldErrorResponse {

    private String field;

    private String message;

    private String objectName;

}
