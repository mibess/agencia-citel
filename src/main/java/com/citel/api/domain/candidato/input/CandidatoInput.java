package com.citel.api.domain.candidato.input;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.citel.api.commons.validations.CPFValidation;
import com.citel.api.commons.validations.DataValidation;
import com.citel.api.domain.candidato.Candidato;
import com.citel.api.domain.candidato.Endereco;
import com.citel.api.domain.candidato.exceptions.CPFInvalidoException;
import com.citel.api.domain.candidato.exceptions.CandidatoException;
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

  @Pattern(regexp = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})$", message = "CPF inválido. Use o formato: 999.999.999-99")
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

  public final void candidatoInputValidation() {
    if (!CPFValidation.isValidCPF(this.getCpf())) {
      throw new CPFInvalidoException("Número de CPF não é válido");
    }

    if (!DataValidation.isDataValid(this.getDataNascimento(), "dd/MM/yyyy")) {
      throw new CandidatoException("Data de Nascimento não é válida");
    }
  }

  public Candidato toCandidato(CandidatoInput candidatoInput) {

    LocalDate nascimentoTratado = LocalDate.parse(
        candidatoInput.getDataNascimento(),
        DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    Endereco enderecoEmb = new Endereco(
        candidatoInput.getCep(),
        candidatoInput.getEndereco(),
        candidatoInput.getNumero(),
        candidatoInput.getBairro(),
        candidatoInput.getCidade(),
        candidatoInput.getEstado());

    Candidato candidato = new Candidato();

    candidato.setNome(candidatoInput.getNome());
    candidato.setCpf(candidatoInput.getCpf());
    candidato.setRg(candidatoInput.getRg());
    candidato.setDataNascimento(nascimentoTratado);
    candidato.setSexo(candidatoInput.getSexo());
    candidato.setMae(candidatoInput.getMae());
    candidato.setPai(candidatoInput.getPai());
    candidato.setEmail(candidatoInput.getEmail());
    candidato.setEndereco(enderecoEmb);
    candidato.setTelefoneFixo(candidatoInput.getTelefoneFixo());
    candidato.setCelular(candidatoInput.getCelular());
    candidato.setAltura(candidatoInput.getAltura());
    candidato.setPeso(candidatoInput.getPeso());
    candidato.setTipoSanguineo(candidatoInput.getTipoSanguineo());

    return candidato;
  }

}
