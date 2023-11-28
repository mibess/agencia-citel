package com.citel.api.domain.candidato.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.citel.api.commons.CalcularIMC;
import com.citel.api.domain.candidato.Candidato;
import com.citel.api.domain.candidato.dto.PercentualDeObesoPorSexoDTO;
import com.citel.api.domain.candidato.usecases.CandidatoObesoPorSexoUseCase;
import com.citel.api.infra.repository.CandidatoRepository;

@Service
public class CandidatoObesoPorSexoService implements CandidatoObesoPorSexoUseCase {

  private CandidatoRepository candidatoRepository;

  public CandidatoObesoPorSexoService(CandidatoRepository candidatoRepository) {
    this.candidatoRepository = candidatoRepository;
  }

  @Override
  public List<PercentualDeObesoPorSexoDTO> listarCandidatosObesoPorSexo() {

    List<Candidato> listaHomens = candidatoRepository.findBySexo("Masculino");
    List<Candidato> listaMulheres = candidatoRepository.findBySexo("Feminino");

    if (listaHomens.isEmpty() && listaMulheres.isEmpty()) {
      return new ArrayList<>();
    }

    double percentualHomens = this.calcularPercentualDeObesidade(listaHomens);
    double percentualMulheres = this.calcularPercentualDeObesidade(listaMulheres);

    List<PercentualDeObesoPorSexoDTO> listaDeObesoPorSexoDTO = new ArrayList<>();

    listaDeObesoPorSexoDTO.add(new PercentualDeObesoPorSexoDTO("Homens", (long) percentualHomens));
    listaDeObesoPorSexoDTO.add(new PercentualDeObesoPorSexoDTO("Mulheres", (long) percentualMulheres));

    return listaDeObesoPorSexoDTO;
  }

  private List<Candidato> listaCanditatosObesos(List<Candidato> listaCandidatos) {

    return listaCandidatos.stream()
        .filter(c -> new CalcularIMC().calculo(c.getPeso(), c.getAltura()) > 30)
        .collect(Collectors.toList());
  }

  private double calcularPercentualDeObesidade(List<Candidato> listaCandidatos) {

    List<Candidato> listaCandidatosObesos = listaCanditatosObesos(listaCandidatos);

    return ((double) listaCandidatosObesos.size() / listaCandidatos.size()) * 100;
  }

}
