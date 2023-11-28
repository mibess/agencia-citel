package com.citel.api.domain.candidato.validations;

import org.springframework.stereotype.Component;

import com.citel.api.domain.candidato.Candidato;
import com.citel.api.domain.candidato.exceptions.CandidatoException;
import com.citel.api.infra.repository.CandidatoRepository;

@Component
public class CandidatoValidation {

  private CandidatoRepository candidatoRepository;

  public CandidatoValidation(CandidatoRepository candidatoRepository) {
    this.candidatoRepository = candidatoRepository;
  }

  public final void validaDadosAntesDeSalvar(Candidato candidato) {
    if (existeCandidatoCadastrado(candidato)) {
      throw new CandidatoException("Candidato já Cadastrado em nossa Base de Dados");
    }

    if (candidatoJaDoou()) {
      throw new CandidatoException("Candidato já fez uma doação");
    }
  }

  private final boolean existeCandidatoCadastrado(Candidato candidato) {
    return candidatoRepository.findByCpf(candidato.getCpf()).isPresent();
  }

  private final boolean candidatoJaDoou() {
    return false;
  }
}
