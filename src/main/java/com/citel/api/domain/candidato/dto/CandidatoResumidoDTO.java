package com.citel.api.domain.candidato.dto;

import com.citel.api.domain.candidato.Candidato;

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

  public static final CandidatoResumidoDTO toCandidatoResumidoDTO(Candidato candidato) {
    return new CandidatoResumidoDTO(
        candidato.getNome(),
        candidato.getCpf(),
        candidato.getEmail(),
        candidato.getTipoSanguineo());
  }
}
