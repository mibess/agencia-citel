package com.citel.api.domain.candidato.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.citel.api.domain.candidato.dto.CandidatoPorEstadoDTO;
import com.citel.api.domain.candidato.exceptions.ListaDeCandidatosVaziaException;
import com.citel.api.domain.candidato.usecases.CandidatoPorEstadoUseCase;
import com.citel.api.infra.repository.CandidatoRepository;

@Service
public class CandidatoPorEstadoService implements CandidatoPorEstadoUseCase {
  private CandidatoRepository candidatoRepository;

  public CandidatoPorEstadoService(CandidatoRepository candidatoRepository) {
    this.candidatoRepository = candidatoRepository;
  }

  @Override
  public List<CandidatoPorEstadoDTO> listarCandidatosPorEstado() {
    return candidatoRepository.findCandidatosByEstado();
  }

  @Override
  public void naoAceitarListaVazia(List<CandidatoPorEstadoDTO> listaCandidatoPorEstadoDTO) {
    if (listaCandidatoPorEstadoDTO.isEmpty()) {
      throw new ListaDeCandidatosVaziaException("Nenhum Candidato Encontrado");
    }
  }

}
