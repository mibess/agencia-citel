package com.citel.api.services.candidato;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citel.api.commons.exceptions.GenericException;
import com.citel.api.http.dto.CandidatoDTO;
import com.citel.api.http.dto.ImportacaoCompletaDTO;
import com.citel.api.models.candidato.Candidato;
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

}
