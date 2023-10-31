package com.citel.api.models.candidato;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "candidatos")
public class Candidato {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;
  private String cpf;
  private String rg;
  private LocalDate dataNascimento;
  private String sexo;
  private String mae;
  private String pai;
  private String email;

  @Embedded
  private Endereco endereco;

  private String telefoneFixo;
  private String celular;
  private Double altura;
  private Long peso;

  private String tipoSanguineo;

  public Long getIdade() {
    Period periodo = Period.between(this.dataNascimento, LocalDate.now());

    return Long.valueOf(periodo.getYears());
  }
}
