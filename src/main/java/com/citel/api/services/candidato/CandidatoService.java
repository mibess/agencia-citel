package com.citel.api.services.candidato;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citel.api.commons.exceptions.GenericException;
import com.citel.api.http.dto.CandidatoDTO;
import com.citel.api.http.dto.CandidatoPorEstadoDTO;
import com.citel.api.http.dto.IdadeMediaPorTipoSanguineo;
import com.citel.api.http.dto.ImcPorFaixaDeIdadeDeDezAnosDTO;
import com.citel.api.http.dto.ImportacaoCompletaDTO;
import com.citel.api.http.dto.PercentualDeObesoPorSexoDTO;
import com.citel.api.models.candidato.Candidato;
import com.citel.api.models.candidato.TipoSanguineo;
import com.citel.api.repository.CandidatoRepository;

@Service
public class CandidatoService {

  @Autowired
  private CandidatoRepository candidatoRepository;

  @Transactional
  public CandidatoDTO salvar(CandidatoDTO candidatoDto) {

    Candidato candidato = candidatoRepository.save(candidatoDto.toCandidato());

    return candidatoDto.fromCandidato(candidato);
  }

  @Transactional
  public ImportacaoCompletaDTO salvarLista(List<CandidatoDTO> listaCandidatoDto) {
    if (listaCandidatoDto.isEmpty()) {
      throw new GenericException("Nenhum Registro para Importar");
    }

    List<Candidato> candidatosSalvos = listaCandidatoDto.stream()
        .map(candidato -> candidato.toCandidato())
        .collect(Collectors.toList());

    candidatoRepository.saveAll(candidatosSalvos);

    return new ImportacaoCompletaDTO(Long.valueOf(candidatosSalvos.size()), "Importação concluída com Sucesso!");

  }

  public List<CandidatoPorEstadoDTO> candidatosPorEstado() {

    return candidatoRepository.findCandidatosByEstado();
  }

  public List<ImcPorFaixaDeIdadeDeDezAnosDTO> candidatosPorImcFaixaDeIdadeDeDezAnos() {

    List<Candidato> listaCandidatos = candidatoRepository.findAll();

    if (listaCandidatos.isEmpty()) {
      throw new GenericException("Nenhum Candidato Encontrado");
    }

    return CandidatoServiceFilter.calcularImcPorFaixaDeIdade(listaCandidatos);

  }

  public List<PercentualDeObesoPorSexoDTO> percentualObesoPorSexo() {

    // Busca todas os Homens e Mulheres do Banco de Dados
    List<Candidato> listaHomens = candidatoRepository.findBySexo("Masculino");
    List<Candidato> listaMulheres = candidatoRepository.findBySexo("Feminino");

    double percentualHomens = CandidatoServiceFilter.calcularPercentualDeObesidade(listaHomens);
    double percentualMulheres = CandidatoServiceFilter.calcularPercentualDeObesidade(listaMulheres);

    List<PercentualDeObesoPorSexoDTO> listaDeObesoPorSexoDTO = new ArrayList<>();

    listaDeObesoPorSexoDTO.add(new PercentualDeObesoPorSexoDTO("Homens", (long) percentualHomens));
    listaDeObesoPorSexoDTO.add(new PercentualDeObesoPorSexoDTO("Mulheres", (long) percentualMulheres));

    return listaDeObesoPorSexoDTO;
  }

  public List<IdadeMediaPorTipoSanguineo> idadeMediaPorTipoSanguineo() {

    List<Candidato> listaCandidatos = candidatoRepository.findAll();

    if (listaCandidatos.isEmpty()) {
      throw new GenericException("Nenhum Candidato Encontrado");
    }

    List<IdadeMediaPorTipoSanguineo> listaIdadePorTipo = new ArrayList<>();

    for (TipoSanguineo tipoSanguineo : TipoSanguineo.values()) {
      Long mediaDeIdade = CandidatoServiceFilter.calcularIdadeMediaPorTipoSanguineo(listaCandidatos,
          tipoSanguineo.getTipo());

      listaIdadePorTipo.add(new IdadeMediaPorTipoSanguineo(tipoSanguineo.getTipo(), mediaDeIdade));
    }

    return listaIdadePorTipo;
  }

}
