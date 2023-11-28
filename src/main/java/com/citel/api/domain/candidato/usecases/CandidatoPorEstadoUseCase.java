package com.citel.api.domain.candidato.usecases;

import java.util.List;

import com.citel.api.domain.candidato.dto.CandidatoPorEstadoDTO;

public interface CandidatoPorEstadoUseCase {

  List<CandidatoPorEstadoDTO> listarCandidatosPorEstado();

  void naoAceitarListaVazia(List<CandidatoPorEstadoDTO> listaCandidatoPorEstadoDTO);
}
