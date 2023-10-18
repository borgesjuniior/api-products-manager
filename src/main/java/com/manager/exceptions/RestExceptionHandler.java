package com.manager.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Void> notFoundHandler() {
    return ResponseEntity.notFound().build();
  }
}
