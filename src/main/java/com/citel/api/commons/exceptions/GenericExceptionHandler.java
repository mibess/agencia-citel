package com.citel.api.commons.exceptions;

import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.citel.api.commons.ErrorResponse;
import com.citel.api.commons.GetMessagesError;
import com.citel.api.commons.exceptions.candidato.CPFInvalidoException;

@RestControllerAdvice
public class GenericExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    List<String> errorMessages = GetMessagesError.getMethodArgumentNotValidException(ex);

    return new ErrorResponse(HttpStatus.BAD_REQUEST, errorMessages);
  }

  @ExceptionHandler(GenericException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleGenericException(GenericException ex) {
    List<String> errorMessages = Collections.singletonList(ex.getMessage());

    return new ErrorResponse(HttpStatus.BAD_REQUEST, errorMessages);

  }

  @ExceptionHandler(CPFInvalidoException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleCPFInvalidoException(CPFInvalidoException ex) {
    List<String> errorMessages = Collections.singletonList(ex.getMessage());

    return new ErrorResponse(HttpStatus.BAD_REQUEST, errorMessages);

  }
}
