package com.citel.api.http.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.citel.api.models.candidato.Candidato;
import com.citel.api.models.candidato.Endereco;
import com.fasterxml.jackson.annotation.JsonProperty;

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

  public Candidato toCandidato() {
    LocalDate nascimentoTratado = LocalDate.parse(dataNasc, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    Endereco enderecoEmb = loadEndereco();

    return new Candidato(
        null,
        nome,
        cpf,
        rg,
        nascimentoTratado,
        sexo,
        mae,
        pai,
        email,
        enderecoEmb,
        telefoneFixo,
        celular,
        altura,
        peso,
        tipoSanguineo);
  }

  public CandidatoDTO fromCandidato(Candidato candidato) {
    return new CandidatoDTO(
        candidato.getNome(),
        candidato.getCpf(),
        candidato.getRg(),
        candidato.getDataNascimento().toString(),
        candidato.getSexo(),
        candidato.getMae(),
        candidato.getPai(),
        candidato.getEmail(),
        candidato.getEndereco().getCep(),
        candidato.getEndereco().getEndereco(),
        candidato.getEndereco().getNumero(),
        candidato.getEndereco().getBairro(),
        candidato.getEndereco().getCidade(),
        candidato.getEndereco().getEstado(),
        candidato.getTelefoneFixo(),
        candidato.getCelular(),
        candidato.getAltura(),
        candidato.getPeso(),
        candidato.getTipoSanguineo());
  }

  private Endereco loadEndereco() {
    return new Endereco(cep, endereco, numero, bairro, cidade, estado);
  }

}
