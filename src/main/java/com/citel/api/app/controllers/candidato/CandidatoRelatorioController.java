package com.citel.api.app.controllers.candidato;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.citel.api.domain.candidato.dto.CandidatoPorEstadoDTO;
import com.citel.api.domain.candidato.dto.IdadeMediaPorTipoSanguineoDTO;
import com.citel.api.domain.candidato.dto.ImcPorFaixaDeIdadeDeDezAnosDTO;
import com.citel.api.domain.candidato.dto.PercentualDeObesoPorSexoDTO;
import com.citel.api.domain.candidato.dto.QuantidadePossivelDoadorDTO;
import com.citel.api.domain.candidato.services.CandidatoIdadeMediaPorTipoSanguineoService;
import com.citel.api.domain.candidato.services.CandidatoObesoPorSexoService;
import com.citel.api.domain.candidato.services.CandidatoPorEstadoService;
import com.citel.api.domain.candidato.services.CandidatoPorImcFaixaDeIdadeDeDezAnosService;
import com.citel.api.domain.candidato.services.CandidatoPossiveisDoadoresService;

@RestController
@RequestMapping("/candidatos")
public class CandidatoRelatorioController {

  private CandidatoPorEstadoService candidatoPorEstadoService;
  private CandidatoPorImcFaixaDeIdadeDeDezAnosService candidatoPorImcFaixaDeIdadeDeDezAnosService;
  private CandidatoObesoPorSexoService candidatoObesoPorSexoService;
  private CandidatoIdadeMediaPorTipoSanguineoService candidatoIdadeMediaPorTipoSanguineoService;
  private CandidatoPossiveisDoadoresService candidatoPossiveisDoadoresService;

  public CandidatoRelatorioController(
      CandidatoPorEstadoService candidatoPorEstadoService,
      CandidatoPorImcFaixaDeIdadeDeDezAnosService candidatoPorImcFaixaDeIdadeDeDezAnosService,
      CandidatoObesoPorSexoService candidatoObesoPorSexoService,
      CandidatoIdadeMediaPorTipoSanguineoService candidatoIdadeMediaPorTipoSanguineoService,
      CandidatoPossiveisDoadoresService candidatoPossiveisDoadoresService) {

    this.candidatoPorEstadoService = candidatoPorEstadoService;
    this.candidatoPorImcFaixaDeIdadeDeDezAnosService = candidatoPorImcFaixaDeIdadeDeDezAnosService;
    this.candidatoObesoPorSexoService = candidatoObesoPorSexoService;
    this.candidatoIdadeMediaPorTipoSanguineoService = candidatoIdadeMediaPorTipoSanguineoService;
    this.candidatoPossiveisDoadoresService = candidatoPossiveisDoadoresService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping("/por-estado")
  public List<CandidatoPorEstadoDTO> listaCandidatosPorEstado() {

    return candidatoPorEstadoService.listarCandidatosPorEstado();
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping("/imc-por-faixa-idade")
  public List<ImcPorFaixaDeIdadeDeDezAnosDTO> imcPorFaixaDeIdade() {

    return candidatoPorImcFaixaDeIdadeDeDezAnosService.listarCandidatosPorImcFaixaDeIdadeDeDezAnos();
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping("/percentual-obesidade-por-sexo")
  public List<PercentualDeObesoPorSexoDTO> percentualObesoPorSexo() {

    return candidatoObesoPorSexoService.listarCandidatosObesoPorSexo();
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping("/idade-media-tipo-sanguineo")
  public List<IdadeMediaPorTipoSanguineoDTO> idadeMediaPorTipoSanguineo() {

    return candidatoIdadeMediaPorTipoSanguineoService.listarPorIdadeMediaETipoSanguineo();

  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping("/possiveis-doadores-por-tipo-sanguineo")
  public List<QuantidadePossivelDoadorDTO> possiveisDoadoresPorTipoSanguineo() {

    return candidatoPossiveisDoadoresService.listarPossiveisDoadores();

  }

}
