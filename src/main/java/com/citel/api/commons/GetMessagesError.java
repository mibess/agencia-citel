package com.citel.api.commons;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class GetMessagesError {

  private GetMessagesError() {
  }

  public static List<String> getMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    return ex.getBindingResult()
        .getAllErrors()
        .stream()
        .map(error -> {
          FieldError fieldError = (FieldError) error;
          return String.format("(field: %s) %s", fieldError.getField(), fieldError.getDefaultMessage());
        })
        .collect(Collectors.toList());
  }
}
