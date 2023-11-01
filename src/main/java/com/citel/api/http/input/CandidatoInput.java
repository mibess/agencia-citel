package com.citel.api.http.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidatoInput {
  @NotBlank
  private String nome;
  @NotBlank
  private String cpf;
  @NotBlank
  private String rg;
  @NotBlank
  private String dataNasc;
  @NotBlank
  private String sexo;
  @NotBlank
  private String mae;
  @NotBlank
  private String pai;
  @NotBlank
  private String email;
  @NotBlank
  private String cep;
  @NotBlank
  private String endereco;
  @NotBlank
  private Long numero;
  @NotBlank
  private String bairro;
  @NotBlank
  private String cidade;
  @NotBlank
  private String estado;
  @NotBlank
  private String telefoneFixo;
  @NotBlank
  private String celular;
  @NotBlank
  private Double altura;
  @NotNull
  private Long peso;
  @NotNull
  private String tipoSanguineo;

}
