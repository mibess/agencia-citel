package com.citel.api.domain.candidato.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.citel.api.commons.CalcularIMC;
import com.citel.api.domain.candidato.Candidato;
import com.citel.api.domain.candidato.dto.ImcPorFaixaDeIdadeDeDezAnosDTO;
import com.citel.api.domain.candidato.usecases.CandidatoPorImcFaixaDeIdadeDeDezAnosUseCase;
import com.citel.api.infra.repository.CandidatoRepository;

@Service
public class CandidatoPorImcFaixaDeIdadeDeDezAnosService implements CandidatoPorImcFaixaDeIdadeDeDezAnosUseCase {

  private static final int FAIXA_DE_IDADE = 10;

  private CandidatoRepository candidatoRepository;
  private CalcularIMC calcularIMC;

  public CandidatoPorImcFaixaDeIdadeDeDezAnosService(CandidatoRepository candidatoRepository, CalcularIMC calcularIMC) {
    this.candidatoRepository = candidatoRepository;
    this.calcularIMC = calcularIMC;
  }

  @Override
  public List<ImcPorFaixaDeIdadeDeDezAnosDTO> listarCandidatosPorImcFaixaDeIdadeDeDezAnos() {
    List<Candidato> listaCandidatos = candidatoRepository.findAll();

    return listaCandidatos.isEmpty() ? new ArrayList<>() : calcularImcPorFaixaDeIdade(listaCandidatos);
  }

  private List<ImcPorFaixaDeIdadeDeDezAnosDTO> calcularImcPorFaixaDeIdade(List<Candidato> listaCandidatos) {
    Long idadeDoMaisVelho = this.idadeDoMaisVelho(listaCandidatos);

    return this.criarListaDosCanditatosPorFaixa(listaCandidatos, idadeDoMaisVelho);
  }

  private List<ImcPorFaixaDeIdadeDeDezAnosDTO> criarListaDosCanditatosPorFaixa(
      List<Candidato> listaCandidatos,
      Long idadeDoMaisVelho) {

    List<ImcPorFaixaDeIdadeDeDezAnosDTO> imcPorFaixaDeIdade = new ArrayList<>();

    for (int idadeAtual = 0; idadeAtual <= idadeDoMaisVelho; idadeAtual += FAIXA_DE_IDADE) {
      int faixaInicio = idadeAtual;
      int faixaFim = idadeAtual + FAIXA_DE_IDADE;

      List<Candidato> listaEntreFaixaIdade = this.listaEntreFaixaIdade(listaCandidatos, faixaInicio, faixaFim);

      if (!listaEntreFaixaIdade.isEmpty()) {
        long valorMedioImc = this.valorMedioImc(listaEntreFaixaIdade);
        String faixaDeIdade = this.faixaDeIdade(faixaInicio, faixaFim);

        imcPorFaixaDeIdade.add(new ImcPorFaixaDeIdadeDeDezAnosDTO(faixaDeIdade, valorMedioImc));
      }
    }

    return imcPorFaixaDeIdade;
  }

  private long valorMedioImc(List<Candidato> listaEntreFaixaIdade) {
    return (long) listaEntreFaixaIdade
        .stream()
        .mapToDouble(e -> calcularIMC.calculo(e.getPeso(), e.getAltura()) / listaEntreFaixaIdade.size())
        .sum();
  }

  private String faixaDeIdade(int inicio, int fim) {
    return "De " + inicio + " a " + fim + " anos";
  }

  private Long idadeDoMaisVelho(List<Candidato> listaCandidatos) {
    return listaCandidatos
        .stream()
        .map(c -> c.getIdade())
        .max(Long::compareTo)
        .orElse(0L);
  }

  private List<Candidato> listaEntreFaixaIdade(List<Candidato> listaCandidatos, int faixaInicio, int faixaFim) {
    return listaCandidatos
        .stream()
        .filter(c -> c.getIdade() >= faixaInicio && c.getIdade() <= faixaFim)
        .collect(Collectors.toList());
  }

}
