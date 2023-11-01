package com.citel.api.http.validations;

import com.citel.api.commons.exceptions.candidato.CPFInvalidoException;
import com.citel.api.commons.validations.CPFValidation;
import com.citel.api.commons.validations.DataValidation;
import com.citel.api.http.input.CandidatoInput;

public class CandidatoValidation {

  private CandidatoValidation() {
  }

  public static void validaDadosAntesDeSalvar(CandidatoInput candidatoInput) {
    if (!CPFValidation.isValidCPF(candidatoInput.getCpf())) {
      throw new CPFInvalidoException("Número de CPF não é válido");
    }

    if (!DataValidation.isDataValid(candidatoInput.getDataNascimento(), "dd/MM/yyyy")) {
      throw new CPFInvalidoException("Número de CPF não é válido");
    }
  }

}
