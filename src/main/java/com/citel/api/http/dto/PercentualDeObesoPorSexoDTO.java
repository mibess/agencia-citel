package com.citel.api.http.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PercentualDeObesoPorSexoDTO {

  private String sexo;
  private Long percentual;

}
