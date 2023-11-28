package com.citel.api.domain.candidato.usecases;

import java.util.List;

import com.citel.api.domain.candidato.dto.ImcPorFaixaDeIdadeDeDezAnosDTO;

public interface CandidatoPorImcFaixaDeIdadeDeDezAnosUseCase {

  List<ImcPorFaixaDeIdadeDeDezAnosDTO> listarCandidatosPorImcFaixaDeIdadeDeDezAnos();

}
