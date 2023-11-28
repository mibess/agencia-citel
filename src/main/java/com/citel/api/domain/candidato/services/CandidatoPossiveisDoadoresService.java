package com.citel.api.domain.candidato.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.citel.api.domain.candidato.Candidato;
import com.citel.api.domain.candidato.TipoSanguineo;
import com.citel.api.domain.candidato.TipoSanguineoRecebedor;
import com.citel.api.domain.candidato.dto.QuantidadePossivelDoadorDTO;
import com.citel.api.domain.candidato.usecases.CandidatoPossiveisDoadoresUseCase;
import com.citel.api.infra.repository.CandidatoRepository;

@Service
public class CandidatoPossiveisDoadoresService implements CandidatoPossiveisDoadoresUseCase {

  private CandidatoRepository candidatoRepository;

  public CandidatoPossiveisDoadoresService(CandidatoRepository candidatoRepository) {

    this.candidatoRepository = candidatoRepository;
  }

  @Override
  public List<QuantidadePossivelDoadorDTO> listarPossiveisDoadores() {
    List<Candidato> listaAdeptosParaDoacao = this.filtrarListaDeCandidatos();

    List<QuantidadePossivelDoadorDTO> quantidadeDeDoadores = new ArrayList<>();

    for (TipoSanguineo tipoSanguineo : TipoSanguineo.values()) {
      Long quantidade = this.filtrarQuantidadeDeDoadores(listaAdeptosParaDoacao, tipoSanguineo.getTipo());

      quantidadeDeDoadores.add(new QuantidadePossivelDoadorDTO(tipoSanguineo.getTipo(), quantidade));
    }

    return quantidadeDeDoadores;
  }

  @Override
  public List<Candidato> filtrarCanditatosPorPesoAcimaDe50Quilos() {
    return candidatoRepository.findByPesoGreaterThan(50L);
  }

  @Override
  public boolean candidatoComIdadeEntre16e69anos(Candidato candidato) {
    return candidato.getIdade() >= 16 && candidato.getIdade() <= 69;
  }

  private List<Candidato> filtrarListaDeCandidatos() {
    List<Candidato> listaCandidatos = this.filtrarCanditatosPorPesoAcimaDe50Quilos();

    return listaCandidatos.isEmpty() ? new ArrayList<>() : this.filtrarAdeptosParaDoacao(listaCandidatos);
  }

  private List<Candidato> filtrarAdeptosParaDoacao(List<Candidato> listaCandidatos) {
    return listaCandidatos
        .stream()
        .filter(this::candidatoComIdadeEntre16e69anos)
        .collect(Collectors.toList());
  }

  private Long filtrarQuantidadeDeDoadores(List<Candidato> listaCandidatos, String tipoSanguineo) {

    List<String> tiposAceitaveis = TipoSanguineoRecebedor.listarTiposAceitaveis(tipoSanguineo);

    List<Candidato> candidatos = listaCandidatos
        .stream()
        .filter(c -> tiposAceitaveis.contains(c.getTipoSanguineo()))
        .collect(Collectors.toList());

    return (long) candidatos.size();

  }

}
