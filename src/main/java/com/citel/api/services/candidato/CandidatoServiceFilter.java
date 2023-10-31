package com.citel.api.services.candidato;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.sql.ast.tree.expression.Collation;

import com.citel.api.http.dto.ImcPorFaixaDeIdadeDeDezAnosDTO;
import com.citel.api.models.candidato.Candidato;

public class CandidatoServiceFilter {

  private CandidatoServiceFilter() {
  }

  private static Long idadeDoMaisVelho(List<Candidato> listaCandidatos) {
    return listaCandidatos.stream()
        .map(c -> c.getIdade())
        .max(Long::compareTo)
        .orElse(0L);
  }

  public static List<ImcPorFaixaDeIdadeDeDezAnosDTO> calcularImcPorFaixaDeIdade(List<Candidato> listaCandidatos) {
    List<ImcPorFaixaDeIdadeDeDezAnosDTO> imcPorFaixaDeIdade = new ArrayList<>();

    Long idadeDoMaisVelho = idadeDoMaisVelho(listaCandidatos);

    for (int i = 0; i <= idadeDoMaisVelho; i += 10) {
      int faixaInicio = i;
      int faixaFim = i + 10;

      List<Candidato> listaEntreFaixaIdade = listaEntreFaixaIdade(listaCandidatos, faixaInicio, faixaFim);

      String faixaDeIdade = "De " + faixaInicio + " a " + faixaFim + " anos";

      double valorMedioImc = listaEntreFaixaIdade.stream()
          .mapToDouble(e -> calcularImc(e.getPeso(), e.getAltura()) / listaEntreFaixaIdade.size()).sum();

      imcPorFaixaDeIdade.add(
          new ImcPorFaixaDeIdadeDeDezAnosDTO(faixaDeIdade, (long) valorMedioImc));
    }

    return imcPorFaixaDeIdade;
  }

  private static List<Candidato> listaEntreFaixaIdade(List<Candidato> listaCandidatos, int faixaInicio, int faixaFim) {
    return listaCandidatos.stream()
        .filter(c -> c.getIdade() >= faixaInicio && c.getIdade() <= faixaFim)
        .collect(Collectors.toList());
  }

  public static List<Candidato> listaCanditatosObesos(List<Candidato> listaCandidatos) {
    return listaCandidatos.stream()
        .filter(c -> calcularImc(c.getPeso(), c.getAltura()) > 30)
        .collect(Collectors.toList());
  }

  private static double calcularImc(Long peso, Double altura) {
    return peso / (altura * altura);
  }

  public static double calcularPercentualDeObesidade(List<Candidato> listaCandidatos) {

    List<Candidato> listaCandidatosObesos = listaCanditatosObesos(listaCandidatos);

    return ((double) listaCandidatosObesos.size() / listaCandidatos.size()) * 100;
  }
}
