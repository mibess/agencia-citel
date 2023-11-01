package com.citel.api.services.candidato;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citel.api.commons.exceptions.GenericException;
import com.citel.api.http.converters.CandidatoConverter;
import com.citel.api.http.dto.CandidatoPorEstadoDTO;
import com.citel.api.http.dto.CandidatoResumidoDTO;
import com.citel.api.http.dto.IdadeMediaPorTipoSanguineo;
import com.citel.api.http.dto.ImcPorFaixaDeIdadeDeDezAnosDTO;
import com.citel.api.http.dto.ImportacaoCompletaDTO;
import com.citel.api.http.dto.PercentualDeObesoPorSexoDTO;
import com.citel.api.http.dto.QuantidadePossivelDoadorDTO;
import com.citel.api.http.input.CandidatoImportarInput;
import com.citel.api.http.input.CandidatoInput;
import com.citel.api.models.candidato.Candidato;
import com.citel.api.models.candidato.TipoSanguineo;
import com.citel.api.repository.CandidatoRepository;

@Service
public class CandidatoService {

  private CandidatoRepository candidatoRepository;

  public CandidatoService(CandidatoRepository candidatoRepository) {
    this.candidatoRepository = candidatoRepository;
  }

  private CandidatoConverter candidatoConverter = new CandidatoConverter();

  @Transactional
  public CandidatoResumidoDTO salvar(CandidatoInput candidatoInput) {

    Candidato candidato = candidatoRepository.save(
        candidatoConverter.fromCandidatoInputToCandidato(candidatoInput));

    return new CandidatoResumidoDTO(
        candidato.getNome(),
        candidato.getCpf(),
        candidato.getEmail(),
        candidato.getTipoSanguineo());
  }

  @Transactional
  public ImportacaoCompletaDTO salvarLista(List<CandidatoImportarInput> listaCandidatoImportarInput) {
    if (listaCandidatoImportarInput.isEmpty()) {
      throw new GenericException("Nenhum Registro para Importar");
    }

    List<Candidato> candidatosSalvos = listaCandidatoImportarInput.stream()
        .map(c -> candidatoConverter.fromCandidatoImportarInputToCandidato(c))
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

  public List<QuantidadePossivelDoadorDTO> listaDePossiveisDoadores() {
    List<Candidato> listaCandidatos = candidatoRepository.findByPesoGreaterThan(50L);

    if (listaCandidatos.isEmpty()) {
      throw new GenericException("Nenhum Candidato com peso adequado foi encontrado");
    }

    List<Candidato> listaAdeptosParaDoacao = CandidatoServiceFilter.listaAdeptosParaDoacao(listaCandidatos);

    List<QuantidadePossivelDoadorDTO> quantidadeDeDoadores = new ArrayList<>();

    for (TipoSanguineo tipoSanguineo : TipoSanguineo.values()) {

      Long quantidade = CandidatoServiceFilter.filtrarDoadores(listaAdeptosParaDoacao, tipoSanguineo.getTipo());

      quantidadeDeDoadores.add(new QuantidadePossivelDoadorDTO(tipoSanguineo.getTipo(), quantidade));
    }

    return quantidadeDeDoadores;
  }

}
