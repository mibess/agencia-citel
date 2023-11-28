package com.citel.api.domain.candidato.usecases;

import java.util.List;

import com.citel.api.domain.candidato.dto.PercentualDeObesoPorSexoDTO;

public interface CandidatoObesoPorSexoUseCase {

  List<PercentualDeObesoPorSexoDTO> listarCandidatosObesoPorSexo();

}
