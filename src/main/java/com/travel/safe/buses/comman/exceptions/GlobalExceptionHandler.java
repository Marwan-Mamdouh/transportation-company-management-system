package com.travel.safe.buses.comman.exceptions;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", LocalDateTime.now()); // Use actual timestamp logic
    body.put("status", status.value());
    body.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
    body.put("message", ex.getBindingResult().getFieldErrors().stream()
        .map(fe -> fe.getField() + " Error: " + fe.getDefaultMessage())
        .collect(Collectors.joining("; "))); // Your custom message
    body.put("path", request.getDescription(false).replace("uri=", "")); // Extract path

    return new ResponseEntity<>(body, headers, status);
  }
}