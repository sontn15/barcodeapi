package com.sh.barcodeapi.advice;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.sh.barcodeapi.exception.BadRequestError;
import com.sh.barcodeapi.exception.InternalServerError;
import com.sh.barcodeapi.exception.ResponseError;
import com.sh.barcodeapi.exception.ResponseException;
import com.sh.barcodeapi.web.rest.response.ErrorResponse;
import com.sh.barcodeapi.web.rest.response.FieldErrorResponse;
import com.sh.barcodeapi.web.rest.response.InvalidInputResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class ExceptionHandleAdvice {

    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<ErrorResponse> handleResponseException(ResponseException e, HttpServletRequest request) {
        ResponseError error = e.getResponseError();
        log.warn("Failed to handling request " + request.getRequestURI() + ": " + error.getMessage(), e);
        String message = error.getMessage();
        return ResponseEntity.status(error.getStatus())
                .body(ErrorResponse.builder()
                        .error(error.getName())
                        .message(message)
                        .build());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        log.warn("Failed to handling request " + request.getRequestURI() + ": " + e.getMessage(), e);
        Set<FieldErrorResponse> errors = new HashSet<>();
        errors.add(FieldErrorResponse.builder()
                .field(e.getParameter().getParameterName())
                .message(e.getMessage()).build());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new InvalidInputResponse(
                        BadRequestError.INVALID_INPUT.getMessage(),
                        BadRequestError.INVALID_INPUT.getName(),
                        errors));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        log.warn("Failed to handling request " + request.getRequestURI() + ": " + e.getMessage(), e);
        Set<FieldErrorResponse> errors = new HashSet<>();
        errors.add(FieldErrorResponse.builder()
                .field(e.getMethod())
                .message(e.getMessage()).build());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new InvalidInputResponse(
                        BadRequestError.INVALID_INPUT.getMessage(),
                        BadRequestError.INVALID_INPUT.getName(),
                        errors));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<InvalidInputResponse> handleValidationException(HttpMessageNotReadableException e, HttpServletRequest request) throws IOException {
        log.warn("Failed to handling request " + request.getContextPath() + ": " + e.getMessage(), e);
        Throwable cause = e.getCause();
        InvalidInputResponse invalidInputResponse = null;
        if (cause instanceof InvalidFormatException) {
            InvalidFormatException invalidFormatException = (InvalidFormatException) cause;
            String fieldPath = invalidFormatException.getPath().stream()
                    .map(JsonMappingException.Reference::getFieldName)
                    .collect(Collectors.joining("."));
            invalidInputResponse = new InvalidInputResponse(
                    BadRequestError.INVALID_INPUT.getMessage(),
                    BadRequestError.INVALID_INPUT.name(),
                    Collections.singleton(FieldErrorResponse.builder()
                            .field(fieldPath)
                            .message("Invalid input format").build())
            );
        }
        if (cause instanceof JsonParseException) {
            JsonParseException jsonParseException = (JsonParseException) cause;
            invalidInputResponse = new InvalidInputResponse(
                    BadRequestError.INVALID_INPUT.getMessage(),
                    BadRequestError.INVALID_INPUT.name(),
                    Collections.singleton(FieldErrorResponse.builder()
                            .field(jsonParseException.getProcessor().getCurrentName())
                            .message(jsonParseException.getMessage()).build())
            );
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(invalidInputResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<InvalidInputResponse> handleValidationException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.warn("Failed to handling request " + request.getRequestURI() + ": " + e.getMessage(), e);
        BindingResult bindingResult = e.getBindingResult();
        Set<FieldErrorResponse> fieldErrors = bindingResult.getAllErrors().stream()
                .map(objectError -> {
                    try {
                        FieldError fieldError = (FieldError) objectError;
                        fieldError.getField();
                        return FieldErrorResponse.builder()
                                .field(fieldError.getField())
                                .message(fieldError.getDefaultMessage())
                                .objectName(fieldError.getObjectName())
                                .build();
                    } catch (ClassCastException ex) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        log.warn("Failed to handling request " + request.getContextPath() + ": " + e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new InvalidInputResponse(
                        BadRequestError.INVALID_INPUT.getMessage(),
                        BadRequestError.INVALID_INPUT.getName(),
                        fieldErrors));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleResponseException(Exception e, HttpServletRequest request) {
        ResponseError error = InternalServerError.INTERNAL_SERVER_ERROR;
        log.error("Failed to handling request " + request.getContextPath() + ": " + error.getMessage(), e);
        return ResponseEntity.status(error.getStatus())
                .body(ErrorResponse.builder()
                        .error(error.getName())
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<InvalidInputResponse> handleValidationException(
            BindException e, HttpServletRequest request) {
        log.warn("Failed to handle request {}: {}", request.getRequestURI(), e.getMessage(), e);
        Set<FieldErrorResponse> fieldsErrors = e.getFieldErrors().stream()
                .map(fieldError -> FieldErrorResponse.builder()
                        .field(fieldError.getField())
                        .objectName(fieldError.getObjectName())
                        .message(fieldError.getDefaultMessage())
                        .build())
                .collect(Collectors.toSet());
        String message = fieldsErrors.stream()
                .map(FieldErrorResponse::getMessage)
                .collect(Collectors.joining(";"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new InvalidInputResponse(
                        message,
                        BadRequestError.INVALID_INPUT.name(),
                        fieldsErrors
                ));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<InvalidInputResponse> handleMissingParams(MissingServletRequestParameterException ex, HttpServletRequest request) {
        log.warn("Failed to handling request " + request.getRequestURI() + ": " + ex.getMessage(), ex);
        Set<FieldErrorResponse> errors = new HashSet<>();
        errors.add(FieldErrorResponse.builder()
                .field(ex.getParameterName())
                .message(ex.getMessage())
                .build());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new InvalidInputResponse(
                        BadRequestError.INVALID_INPUT.getMessage(),
                        BadRequestError.INVALID_INPUT.getName(),
                        errors));
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<Object> handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException e, HttpServletRequest request) {
        log.warn("Failed to handling request " + request.getContextPath() + ": " + e.getCause().getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .message(e.getCause().getMessage())
                        .error(BadRequestError.INVALID_INPUT.name())
                        .build());
    }
}
