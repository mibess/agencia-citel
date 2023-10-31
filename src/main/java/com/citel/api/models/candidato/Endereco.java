package com.citel.api.models.candidato;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

  @Column(name = "cep")
  private String cep;

  @Column(name = "endereco")
  private String endereco;

  @Column(name = "numero")
  private Long numero;

  @Column(name = "bairro")
  private String bairro;

  @Column(name = "cidade")
  private String cidade;

  @Column(name = "estado")
  private String estado;

}
