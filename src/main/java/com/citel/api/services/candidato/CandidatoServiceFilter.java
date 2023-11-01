package com.citel.api.services.candidato;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.citel.api.commons.statics.TipoSanguineoRecebedor;
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

  public static List<Candidato> listaPorTipoSanguineo(List<Candidato> listaCandidatos, String tipoSanguineo) {
    return listaCandidatos.stream()
        .filter(c -> c.getTipoSanguineo().equals(tipoSanguineo))
        .collect(Collectors.toList());
  }

  public static Long calcularIdadeMediaPorTipoSanguineo(List<Candidato> listaCandidatos, String tipoSanguineo) {
    List<Candidato> candidatos = CandidatoServiceFilter.listaPorTipoSanguineo(listaCandidatos, tipoSanguineo);

    Long mediaDeIdade = candidatos.stream().mapToLong(c -> c.getIdade()).sum();

    mediaDeIdade = (!candidatos.isEmpty()) ? mediaDeIdade / candidatos.size() : mediaDeIdade;
    return mediaDeIdade;
  }

  public static List<Candidato> listaAdeptosParaDoacao(List<Candidato> listaCandidatos) {
    return listaCandidatos.stream()
        .filter(c -> c.getIdade() >= 16 && c.getIdade() <= 69)
        .collect(Collectors.toList());
  }

  public static Long filtrarDoadores(List<Candidato> listaCandidatos, String tipoSanguineo) {

    List<String> tiposAceitaveis = TipoSanguineoRecebedor.aceita(tipoSanguineo);
    List<Candidato> candidatos = listaCandidatos.stream()
        .filter(c -> tiposAceitaveis.contains(c.getTipoSanguineo()))
        .collect(Collectors.toList());

    return (long) candidatos.size();

  }

}