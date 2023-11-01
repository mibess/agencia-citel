package com.citel.api.http.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidatoResumidoDTO {
  private String nome;
  private String cpf;
  private String email;
  private String tipoSanguineo;
}
