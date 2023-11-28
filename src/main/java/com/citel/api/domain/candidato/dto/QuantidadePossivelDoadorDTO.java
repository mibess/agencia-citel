package com.citel.api.domain.candidato.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuantidadePossivelDoadorDTO {
  private String tipoSanguineo;
  private Long quantidade;
}
