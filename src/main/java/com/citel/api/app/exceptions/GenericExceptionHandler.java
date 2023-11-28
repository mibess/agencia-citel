package com.citel.api.app.exceptions;

import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.citel.api.domain.candidato.exceptions.CPFInvalidoException;
import com.citel.api.domain.candidato.exceptions.CandidatoException;
import com.citel.api.domain.candidato.exceptions.ListaDeCandidatosVaziaException;

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

  @ExceptionHandler(ListaDeCandidatosVaziaException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleListaDeCandidatosVaziaException(ListaDeCandidatosVaziaException ex) {
    List<String> errorMessages = Collections.singletonList(ex.getMessage());

    return new ErrorResponse(HttpStatus.BAD_REQUEST, errorMessages);

  }

  @ExceptionHandler(CandidatoException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleCandidatoException(CandidatoException ex) {
    List<String> errorMessages = Collections.singletonList(ex.getMessage());

    return new ErrorResponse(HttpStatus.BAD_REQUEST, errorMessages);

  }
}
