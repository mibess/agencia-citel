package com.citel.api.domain.candidato.input;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidatoImportarInput {
  private String nome;
  private String cpf;
  private String rg;
  @JsonProperty("data_nasc")
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
  @JsonProperty("telefone_fixo")
  private String telefoneFixo;
  private String celular;
  private Double altura;
  private Long peso;
  @JsonProperty("tipo_sanguineo")
  private String tipoSanguineo;

}
