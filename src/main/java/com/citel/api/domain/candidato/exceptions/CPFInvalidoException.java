package com.citel.api.domain.candidato.exceptions;

public class CPFInvalidoException extends RuntimeException {
  public CPFInvalidoException(String message) {
    super(message);
  }
}
