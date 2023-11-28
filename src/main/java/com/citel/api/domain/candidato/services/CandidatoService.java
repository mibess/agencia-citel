package com.citel.api.domain.candidato.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citel.api.app.converters.candidato.CandidatoConverter;
import com.citel.api.app.exceptions.GenericException;
import com.citel.api.core.importacao.ImportacaoConfig;
import com.citel.api.core.importacao.ImportacaoConfig.TipoImportacao;
import com.citel.api.domain.candidato.Candidato;
import com.citel.api.domain.candidato.dto.CandidatoResumidoDTO;
import com.citel.api.domain.candidato.dto.ImportacaoCompletaDTO;
import com.citel.api.domain.candidato.input.CandidatoImportarInput;
import com.citel.api.domain.candidato.input.CandidatoInput;
import com.citel.api.domain.candidato.usecases.CandidatoUseCase;
import com.citel.api.domain.candidato.validations.CandidatoImportacaoValidation;
import com.citel.api.domain.candidato.validations.CandidatoValidation;
import com.citel.api.infra.http.client.CandidatoJson;
import com.citel.api.infra.http.client.CandidatosJsonAWS;
import com.citel.api.infra.http.client.CandidatosJsonLocal;
import com.citel.api.infra.repository.CandidatoRepository;

@Service
public class CandidatoService implements CandidatoUseCase {

  private final CandidatoRepository candidatoRepository;
  private final CandidatoConverter candidatoConverter;

  private final CandidatoValidation candidatoValidation;
  private final CandidatoImportacaoValidation candidatoImportacaoValidation;

  private final ImportacaoConfig importacaoConfig;

  public CandidatoService(
      CandidatoRepository candidatoRepository,
      CandidatoConverter candidatoConverter,
      CandidatoValidation candidatoValidation,
      CandidatoImportacaoValidation candidatoImportacaoValidation,
      ImportacaoConfig importacaoConfig) {

    this.candidatoRepository = candidatoRepository;
    this.candidatoConverter = candidatoConverter;
    this.candidatoValidation = candidatoValidation;
    this.candidatoImportacaoValidation = candidatoImportacaoValidation;
    this.importacaoConfig = importacaoConfig;
  }

  @Override
  @Transactional
  public CandidatoResumidoDTO criarCandidato(CandidatoInput candidatoInput) {

    candidatoInput.candidatoInputValidation();

    Candidato candidato = candidatoInput.toCandidato(candidatoInput);

    candidatoValidation.validaDadosAntesDeSalvar(candidato);

    candidatoRepository.save(candidato);

    return new CandidatoResumidoDTO(
        candidato.getNome(),
        candidato.getCpf(),
        candidato.getEmail(),
        candidato.getTipoSanguineo());
  }

  @Override
  public CandidatoResumidoDTO alterarCandidato(CandidatoInput candidatoInput) {

    throw new UnsupportedOperationException("Unimplemented method 'alterarCandidato'");
  }

  @Override
  public void excluirCandidato(Long candidatoId) {

    throw new UnsupportedOperationException("Unimplemented method 'excluirCandidato'");
  }

  @Override
  public List<CandidatoResumidoDTO> listarTodosCandidatos() {
    List<Candidato> candidatos = candidatoRepository.findAll();

    return candidatos.stream()
        .map(CandidatoResumidoDTO::toCandidatoResumidoDTO)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public ImportacaoCompletaDTO salvarListaDeCandidatos() {

    CandidatoJson candidatoJson = criarInstanciaCandidatoJson();

    List<CandidatoImportarInput> listaCandidatoImportarInput = candidatoJson.importarCandidatos();

    candidatoImportacaoValidation.validarImportacaoAntesDeSalvar(listaCandidatoImportarInput);

    List<Candidato> candidatos = listaCandidatoImportarInput
        .stream()
        .map(candidatoConverter::fromCandidatoImportarInputToCandidato)
        .collect(Collectors.toList());

    candidatoRepository.saveAll(candidatos);

    return new ImportacaoCompletaDTO(Long.valueOf(candidatos.size()), "Importação concluída com Sucesso!");

  }

  private CandidatoJson criarInstanciaCandidatoJson() {
    return TipoImportacao.AWS_S3.equals(importacaoConfig.getTipoImportacao())
        ? new CandidatosJsonAWS()
        : new CandidatosJsonLocal();
  }

}
