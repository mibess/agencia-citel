package com.citel.api.domain.candidato.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IdadeMediaPorTipoSanguineoDTO {
  private String tipoSanguineo;
  private Long idadeMedia;
}
