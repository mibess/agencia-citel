package com.citel.api.domain.candidato.usecases;

import java.util.List;

import com.citel.api.domain.candidato.dto.IdadeMediaPorTipoSanguineoDTO;

public interface CandidatoIdadeMediaPorTipoSanguineoUseCase {

  List<IdadeMediaPorTipoSanguineoDTO> listarPorIdadeMediaETipoSanguineo();

}
