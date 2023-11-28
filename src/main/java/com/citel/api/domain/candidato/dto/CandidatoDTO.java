package com.citel.api.domain.candidato.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidatoDTO {
  private String nome;
  private String cpf;
  private String rg;
  private String dataNasc;
  private String sexo;
  private String mae;
  private String pai;
  private String email;
  private String cep;
  private String endereco;
  private Long numero;
  private String bairro;
  private String cidade;
  private String estado;
  private String telefoneFixo;
  private String celular;
  private Double altura;
  private Long peso;
  private String tipoSanguineo;

}
