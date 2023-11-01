package com.citel.api.http.input;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidatoInput {
  @Size(min = 3, max = 255, message = "Nome deve ter entre 3 e 255 de caracteres")
  @NotBlank(message = "O nome não pode ser vazio")
  private String nome;

  @NotBlank(message = "O CPF não pode ser vazio")
  private String cpf;

  @NotBlank(message = "RG não pode ser vazio")
  private String rg;

  @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "Formato de data inválido. Use dd/MM/yyyy")
  @NotBlank(message = "Data de Nascimento Inválida")
  @JsonProperty("data_nasc")
  private String dataNascimento;

  @Pattern(regexp = "Masculino|Feminino", message = "O sexo deve ser 'Masculino' ou 'Feminino'")
  @NotBlank(message = "O sexo não pode ser vazio")
  private String sexo;

  @NotBlank(message = "Nome da mãe não pode ser vazio")
  private String mae;

  @NotBlank(message = "Nome do pai não pode ser vazio")
  private String pai;

  @NotBlank(message = "Email não pode ser vazio")
  private String email;

  @NotBlank(message = "CEP não pode ser vazio")
  private String cep;

  @NotBlank(message = "Endereço não pode ser vazio")
  private String endereco;

  @NotNull(message = "Número não pode ser vazio")
  private Long numero;

  @NotBlank(message = "Bairro não pode ser vazio")
  private String bairro;

  @NotBlank(message = "Cidade não pode ser vazio")
  private String cidade;

  @NotBlank(message = "Estado não pode ser vazio")
  private String estado;

  @NotBlank(message = "Telefone fixo não pode ser vazio")
  @JsonProperty("telefone_fixo")
  private String telefoneFixo;

  @NotBlank(message = "Celular não pode ser vazio")
  private String celular;

  @NotNull(message = "Altura não pode ser vazio")
  private Double altura;

  @NotNull(message = "Peso não pode ser vazio")
  private Long peso;

  @Size(min = 2, max = 3, message = "Tipo Sanguíneo inválido")
  @NotBlank(message = "Tipo Sanguíneo não pode ser vazio")
  @JsonProperty("tipo_sanguineo")
  private String tipoSanguineo;

}
