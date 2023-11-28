package com.citel.api.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.citel.api.domain.candidato.Candidato;
import com.citel.api.domain.candidato.Endereco;

public class CandidatoUtilsTest {

  public static Candidato getCandidato() {
    Endereco endereco = new Endereco();
    endereco.setCep("59123-032");
    endereco.setBairro("Pajuçara");
    endereco.setEndereco("Rua Santa Margarida");
    endereco.setNumero(628L);
    endereco.setCidade("Natal");
    endereco.setEstado("RN");

    return new Candidato(
        null,
        "Melissa Hadassa da Mota",
        "578.509.506-82",
        "19.244.771-3",
        LocalDate.parse("1954-08-21"),
        "Feminino",
        "Isabel Sebastiana",
        "Marcos Vinicius Cauê da Mota",
        "mmelissahadassadamota@ouplook.com",
        endereco,
        "(84) 2507-8135",
        "(84) 99177-0537",
        1.83,
        47L,
        "B+");
  }

  public static List<Candidato> getListaCandidato() {
    List<Candidato> listaCandidatos = new ArrayList<>();

    listaCandidatos.add(getCandidato());
    listaCandidatos.add(getCandidato());

    return listaCandidatos;
  }
}
