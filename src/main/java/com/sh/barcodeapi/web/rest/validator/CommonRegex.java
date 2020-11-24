package com.sh.barcodeapi.web.rest.validator;

public final class CommonRegex {
    public final static String UUID_REGEX = "^(?i)([0-9A-F]{8}-[0-9A-F]{4}-[4][0-9A-F]{3}-[89AB][0-9A-F]{3}-[0-9A-F]{12})$";
    public final static String BOOLEAN_REGEX = "(?i)(true|false|1|0)";
}
