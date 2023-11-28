package com.citel.api.domain.candidato.validations;

import java.util.List;

import org.springframework.stereotype.Component;

import com.citel.api.app.exceptions.GenericException;
import com.citel.api.domain.candidato.input.CandidatoImportarInput;
import com.citel.api.infra.repository.CandidatoRepository;

@Component
public class CandidatoImportacaoValidation {

  private CandidatoRepository candidatoRepository;

  public CandidatoImportacaoValidation(CandidatoRepository candidatoRepository) {
    this.candidatoRepository = candidatoRepository;
  }

  public void validarImportacaoAntesDeSalvar(List<CandidatoImportarInput> listaCandidatoImportarInput) {
    if (listaCandidatoImportarInput.isEmpty()) {
      throw new GenericException("Nenhum Registro para Importar");
    }

    Long quantidadeDeCandidatos = this.quantidadeDeCandidatos();

    if (quantidadeDeCandidatos > 0) {
      throw new GenericException("Sua importação já foi realizada!");
    }

  }

  private Long quantidadeDeCandidatos() {
    return candidatoRepository.count();
  }
}
