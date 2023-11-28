package com.citel.api.domain.candidato.usecases;

import java.util.List;

import com.citel.api.domain.candidato.Candidato;
import com.citel.api.domain.candidato.dto.QuantidadePossivelDoadorDTO;

public interface CandidatoPossiveisDoadoresUseCase {

  List<QuantidadePossivelDoadorDTO> listarPossiveisDoadores();

  List<Candidato> filtrarCanditatosPorPesoAcimaDe50Quilos();

  boolean candidatoComIdadeEntre16e69anos(Candidato candidato);

}
