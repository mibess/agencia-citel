package com.citel.api.domain.candidato.usecases;

import java.util.List;

import com.citel.api.domain.candidato.dto.CandidatoResumidoDTO;
import com.citel.api.domain.candidato.dto.ImportacaoCompletaDTO;
import com.citel.api.domain.candidato.input.CandidatoInput;

public interface CandidatoUseCase {

  CandidatoResumidoDTO criarCandidato(CandidatoInput candidatoInput);

  CandidatoResumidoDTO alterarCandidato(CandidatoInput candidatoInput);

  void excluirCandidato(Long candidatoId);

  List<CandidatoResumidoDTO> listarTodosCandidatos();

  ImportacaoCompletaDTO salvarListaDeCandidatos();
}
