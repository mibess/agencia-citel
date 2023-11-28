package com.citel.api.infra.http.client;

import java.util.List;

import com.citel.api.domain.candidato.input.CandidatoImportarInput;

public interface CandidatoJson {
  List<CandidatoImportarInput> importarCandidatos();
}
