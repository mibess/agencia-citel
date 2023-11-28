package com.citel.api.domain.candidato.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.citel.api.domain.candidato.Candidato;
import com.citel.api.domain.candidato.TipoSanguineo;
import com.citel.api.domain.candidato.dto.IdadeMediaPorTipoSanguineoDTO;
import com.citel.api.domain.candidato.usecases.CandidatoIdadeMediaPorTipoSanguineoUseCase;
import com.citel.api.infra.repository.CandidatoRepository;

@Service
public class CandidatoIdadeMediaPorTipoSanguineoService implements CandidatoIdadeMediaPorTipoSanguineoUseCase {

  private CandidatoRepository candidatoRepository;

  public CandidatoIdadeMediaPorTipoSanguineoService(CandidatoRepository candidatoRepository) {
    this.candidatoRepository = candidatoRepository;
  }

  @Override
  public List<IdadeMediaPorTipoSanguineoDTO> listarPorIdadeMediaETipoSanguineo() {

    List<Candidato> listaCandidatos = candidatoRepository.findAll();

    if (listaCandidatos.isEmpty()) {
      return new ArrayList<>();
    }

    List<IdadeMediaPorTipoSanguineoDTO> listaIdadePorTipo = new ArrayList<>();

    for (TipoSanguineo tipoSanguineo : TipoSanguineo.values()) {
      Long mediaDeIdade = this.calcularIdadeMediaPorTipoSanguineo(listaCandidatos, tipoSanguineo.getTipo());

      listaIdadePorTipo.add(new IdadeMediaPorTipoSanguineoDTO(tipoSanguineo.getTipo(), mediaDeIdade));
    }

    return listaIdadePorTipo;
  }

  private Long calcularIdadeMediaPorTipoSanguineo(List<Candidato> listaCandidatos, String tipoSanguineo) {
    List<Candidato> candidatos = this.listaPorTipoSanguineo(listaCandidatos, tipoSanguineo);

    Long mediaDeIdade = candidatos.stream().mapToLong(c -> c.getIdade()).sum();
    mediaDeIdade = (!candidatos.isEmpty()) ? mediaDeIdade / candidatos.size() : mediaDeIdade;

    return mediaDeIdade;
  }

  private List<Candidato> listaPorTipoSanguineo(List<Candidato> listaCandidatos, String tipoSanguineo) {
    return listaCandidatos
        .stream()
        .filter(c -> c.getTipoSanguineo().equals(tipoSanguineo))
        .collect(Collectors.toList());
  }

}
